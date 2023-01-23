package aglaia.telegramBot.service;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.mapping.KangTaskDtoMapper;
import aglaia.telegramBot.mapping.KangTaskMapper;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.repository.KangTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KangTaskServiceTest {
    @Mock
    private KangTaskRepository kangTaskRepository;
    @Mock
    private KangTaskMapper kangTaskMapper;
    @Mock
    private KangTaskDtoMapper kangTaskDtoMapper;

    private KangTaskService kangTaskService;

    @BeforeEach
    private void init() {
        kangTaskService = new KangTaskServiceImpl(kangTaskRepository, kangTaskMapper, kangTaskDtoMapper);
    }

    // getFirst - 2 cases: if exist, if not exist
    @Test
    public void getFirstIfExist() {

        final var idKangTask = 1L;

        doReturn(idKangTask)
                .when(kangTaskRepository)
                .findIdOfFirst();

        final var kangTask = KangTask.builder()
                .id(idKangTask)
                .problem("blabla12")
                .build();

        doReturn(Optional.of(kangTask))
                .when(kangTaskRepository)
                .findById(idKangTask);

        final var res = kangTaskService.getFirst();

        assertEquals(res, kangTask);
    }

    @Test
    public void getFirstIfNull() {

        final var idKangTask = 1L;

        doReturn(idKangTask)
                .when(kangTaskRepository)
                .findIdOfFirst();

        final Optional<KangTask> empty = Optional.empty();
        doReturn(empty)
                .when(kangTaskRepository)
                .findById(anyLong());

        final Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    kangTaskService.getFirst();
                });
    }

    // getDtoById - 2 cases: if exist , if not exist

    @Test
    public void getDtoByIdIfTaskExist() {

        final var idKangTask = 1L;
        final var kangTask = KangTask.builder()
                .id(idKangTask)
                .problem("blabla12")
                .build();

        final var kangTaskDto = KangTaskDto.builder()
                .id(idKangTask)
                .problem("blabla12")
                .build();

        doReturn(Optional.of(kangTask))
                .when(kangTaskRepository)
                .findById(idKangTask);

        doReturn(kangTaskDto)
                .when(kangTaskMapper)
                .convert(kangTask);

        final var getDtoByIdResult = kangTaskService.getDtoById(idKangTask);

        assertEquals(getDtoByIdResult, kangTaskDto);
    }

    @Test
    public void getDtoByIfTaskNotExist() {

        final var idKangTask = 1L;
        final Optional<KangTask> empty = Optional.empty();
        doReturn(empty)
                .when(kangTaskRepository)
                .findById(anyLong());


        final var getDtoByIdResult = kangTaskService.getDtoById(idKangTask);

        assertNull(getDtoByIdResult);
    }

    // save
    // 1 case test if this task already exist in database - so we check also privet method isThisTaskExist
    // 2 case test if task really new && it's first task in database (there is no "last" task
    // 3 case test if task new && last task exist - so should take last task and write new task as "next"

    @Test
    public void saveIfTaskAlreadyExist() {
        final var idKangTask = 1L;
        final var kangTask = KangTask.builder()
                .id(idKangTask)
                .problem("blabla12")
                .build();

        doReturn(Optional.of(kangTask))
                .when(kangTaskRepository)
                .findByProblem("blabla12");

        final var kangTaskDto = KangTaskDto.builder()
                .id(idKangTask)
                .problem("blabla12")
                .build();

        doReturn(kangTask)
                .when(kangTaskDtoMapper)
                .convert(kangTaskDto);

        final var res = kangTaskService.save(kangTaskDto);
        assertFalse(res);

    }

    @Test
    public void saveIfFirstTaskInDatabase() {
        doReturn(Optional.empty())
                .when(kangTaskRepository)
                .findByEmptyNext();

        final var kangTaskDto = KangTaskDto.builder().id(1L).build();
        final var kangTask = KangTask.builder().id(1L).build();
        doReturn(kangTask)
                .when(kangTaskDtoMapper)
                .convert(kangTaskDto);

        final var res = kangTaskService.save(kangTaskDto);
        assertTrue(res);

    }

    @Test
    public void saveIfNewAndNotFirst() {
        final var kangTaskId = 11L;
        final var previousId = 10L;

        final var kangTaskPrevious = KangTask.builder().id(previousId).build();
        doReturn(Optional.of(kangTaskPrevious))
                .when(kangTaskRepository)
                .findByEmptyNext();

        final var kangTaskDto = KangTaskDto.builder().id(kangTaskId).build();
        final var kangTask = KangTask.builder().id(kangTaskId).build();
        doReturn(kangTask)
                .when(kangTaskDtoMapper)
                .convert(kangTaskDto);

        final var res = kangTaskService.save(kangTaskDto);
        assertTrue(res);
        assertEquals(kangTaskPrevious.getNext(), kangTask);
    }

    // delete, 3 cases
    // I. if there is no task by such id return false
    @Test
    public void deleteReturnFalseIfNoSuchTask() {
        final Optional<KangTask> repositoryResponse = Optional.empty();
        doReturn(repositoryResponse)
                .when(kangTaskRepository)
                .findById(anyLong());

        final var expected = kangTaskService.delete(1L);
        assertFalse(expected);
    }

    // II. if such task exist and previous task exist - so:
    // 1 previous task changed field nextkang
    // 2 method save invoke
    // 3 method delete invoke
    // 4 method return true
    @Test
    public void deleteIfPreviousPresent() {

        final var idKangTask11 = 11L;
        final var idPrevious10 = 10L;
        final var idNext12 = 12L;

        final var kangTask12 = KangTask.builder()
                .id(idNext12)
                .problem("blabla12")
                .build();

        final var kangTask11 = KangTask.builder()
                .id(idKangTask11)
                .problem("blabla11")
                .next(kangTask12)
                .build();

        final var kangTask10 = KangTask.builder()
                .id(idPrevious10)
                .problem("kangTask10")
                .next(kangTask11)
                .build();

        doReturn(Optional.of(kangTask11))
                .when(kangTaskRepository)
                .findById(idKangTask11);

        doReturn(Optional.of(kangTask10))
                .when(kangTaskRepository)
                .findPrevious(idKangTask11);

        final ArgumentCaptor<KangTask> argumentCaptor = ArgumentCaptor.forClass(KangTask.class);
        final ArgumentCaptor<KangTask> argumentCaptor2 = ArgumentCaptor.forClass(KangTask.class);

        final var deleteResult = kangTaskService.delete(idKangTask11);

        // 1 test if previous task changed field nextkang
        assertEquals(kangTask10.getNext().getId(), idNext12);

        // 2 test if to save go invoke obj
        verify(kangTaskRepository).save(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getNext().getId(), idNext12);

        // 3 test if method delete invoke
        verify(kangTaskRepository).delete(argumentCaptor2.capture());
        assertEquals(argumentCaptor2.getValue().getId(), idKangTask11);

        // 4 test if method return true
        assertTrue(deleteResult);
    }

    // III. if such task exist && if it's first - so:
    // 1 method delete obj invoke
    // 2 return true
    @Test
    public void deleteIfFirst() {
        final var idKangTask11 = 11L;
        final var idNext12 = 12L;

        final var kangTask12 = KangTask.builder()
                .id(idNext12)
                .problem("blabla12")
                .build();

        final var kangTask11 = KangTask.builder()
                .id(idKangTask11)
                .problem("blabla11")
                .next(kangTask12)
                .build();

        doReturn(Optional.of(kangTask11))
                .when(kangTaskRepository)
                .findById(idKangTask11);

        final ArgumentCaptor<KangTask> argumentCaptor = ArgumentCaptor.forClass(KangTask.class);
        boolean deleteResult = kangTaskService.delete(idKangTask11);

        // 1 test if method delete obj invoke
        verify(kangTaskRepository).delete(argumentCaptor.capture());
        final var capturedValue = argumentCaptor.getValue();
        assertEquals(capturedValue.getNext().getId(), idNext12);

        // 2 test if method return true
        assertTrue(deleteResult);
    }

    // getAllDto
    @Test
    public void getAllDtoTest() {
        final List<KangTask> repositoryResponse = List.of(KangTask.builder()
                .id(1L)
                .problem("pr")
                .build());

        doReturn(repositoryResponse).when(kangTaskRepository).findAll();
        final List<KangTaskDto> res = kangTaskService.getAllDto();

        // тут проверяем что вызывается нужное количество раз
        verify(kangTaskRepository, times(1)).findAll();
        verify(kangTaskMapper, times(repositoryResponse.size())).convert(any());
    }

}

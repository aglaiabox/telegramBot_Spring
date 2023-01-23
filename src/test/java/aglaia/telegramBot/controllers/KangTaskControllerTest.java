package aglaia.telegramBot.controllers;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.service.KangTaskService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

@WebMvcTest(KangTaskController.class)
class KangTaskControllerTest {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KangTaskService kangTaskService;

    @Test
    void sandboxPrintMain() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/main1"));
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();
        assertEquals(s, "hello world");
    }

    @Test
    void getAll() throws Exception {
        final List<KangTaskDto> list1 = List.of(
                KangTaskDto.builder().id(1L).build(),
                KangTaskDto.builder().id(2L).build()
        );

        doReturn(list1)
                .when(kangTaskService)
                .getAllDto();

        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/getAll"));
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();

        final List<KangTaskDto> list = MAPPER.readValue(s, new TypeReference<List<KangTaskDto>>() {
        });

        assertEquals(list1, list);
    }

    @Test
    void getById() throws Exception {
        final var id = 1L;
        final var kangTaskDto = KangTaskDto.builder().id(id).build();

        doReturn(Optional.of(kangTaskDto))
                .when(kangTaskService)
                .getDtoById(id);

        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/get/{id}", id)
                .param("id", "value"));
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final String s = mvcResult.getResponse().getContentAsString();

        final var res = MAPPER.readValue(s, KangTaskDto.class);

        assertEquals(kangTaskDto, res);
    }

    @Test
    void getByIdIfNoSuchTask() throws Exception {
        final var id = 1L;
//        final KangTaskDto kangTaskDto = null;

        doReturn(Optional.empty())
                .when(kangTaskService)
                .getDtoById(id);

        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/get/{id}", id)
                .param("id", "value"));
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final String s = mvcResult.getResponse().getContentAsString();
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }


    @Test
    void postAddNewIfExist() throws Exception {
        final var kangTaskDto = KangTaskDto.builder().build();
        doReturn(false)
                .when(kangTaskService)
                .save(kangTaskDto);

        String body = MAPPER.writeValueAsString(kangTaskDto);


        final ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/addNew")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();
        assertEquals(KangTaskController.KANG_TASK_ALREADY_EXIST, s);

    }

    @Test
    void postAddNewIfNotExist() throws Exception {
        final var kangTaskDto = KangTaskDto.builder().build();
        doReturn(true)
                .when(kangTaskService)
                .save(kangTaskDto);

        final var body = MAPPER.writeValueAsString(kangTaskDto);

        final ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/addNew")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                );
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();
        assertEquals(KangTaskController.KANG_TASK_SAVED, s);

    }

    @Test
    void postDeleteIfNotExist() throws Exception {
        final var id = 1L;
        doReturn(false)
                .when(kangTaskService)
                .delete(id);

        final ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/delete")
                        .content(String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                );
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();
        assertEquals(KangTaskController.ERROR_DELETE_NO_SUCH_TASK_IN_DATABASE, s);
    }

    @Test
    void postDeleteIfExist() throws Exception {
        final var id = 1L;
        doReturn(true)
                .when(kangTaskService)
                .delete(id);

        final ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/delete")
                        .content(String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON)
                );
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();
        assertEquals(KangTaskController.KANG_TASK_DELETED, s);
    }
}
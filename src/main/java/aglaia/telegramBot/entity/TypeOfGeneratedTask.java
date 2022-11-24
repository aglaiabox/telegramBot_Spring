package aglaia.telegramBot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter
@NoArgsConstructor
public enum TypeOfGeneratedTask {
//    @Column
//    @ManyToMany
    DIVIDE, MULTIPLY;

//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

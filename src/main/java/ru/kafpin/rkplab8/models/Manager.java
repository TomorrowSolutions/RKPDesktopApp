package ru.kafpin.rkplab8.models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * Класс менеджера со свойствами <b>id</b>,<b>surname</b>,<b>name</b>,<b>patronymic</b>,
 * <b>education</b>,<b>category</b>,<b></b>,<b>categoryId</b>,<b>dateOfStart</b>,<b>accountNumber</b>
 * <p>
 *     Данный класс позволяет описать менеджера.
 * </p>
 * <p>
 *     Так же данный класс служит хранилищем для информации из базы данных (MVC)
 * </p>
 * @author Вадим Гуреев
 * @version 1.0
 */
@Data
public class Manager {
    /**Поле id*/
    private int id;
    /**Поле фамилия*/
    private String surname;
    /**Поле имя*/
    private String name;
    /**Поле отчетство*/
    private String patronymic;
    /**Поле образование*/
    private String education;
    /**Поле категория, представленное классом {@link Category}*/
    private Category category;
    /**Поле идентификатор категории*/
    private int categoryId;
    /**Поле дата начала работы*/
    private LocalDate dateOfStart;
    /**Поле номер счета*/
    private String accountNumber;
    /**Полный конструктор предназначен для выборки данных из базы, изменения или удаления записи в базе данных
     * @param id идентификатор
     * @param surname фамилия
     * @param name имя
     * @param patronymic отчество
     * @param education образование
     * @param category категория
     * @param categoryId  идентификатор категории
     * @param dateOfStart  дата начала работы
     * @param accountnumber номер счета
     * @see Manager#Manager(String, String, String, String, Category, int, LocalDate, String)
     * @see Manager#Manager()
     * **/
    public Manager(int id, String surname, String name, String patronymic, String education, Category category, int categoryId, LocalDate dateOfStart, String accountnumber) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.education = education;
        this.category = category;
        this.categoryId = categoryId;
        this.dateOfStart = dateOfStart;
        this.accountNumber = accountnumber;
    }
    /**Конструктор без <b>id</b> предназначен для добавления новой записи в базу
     * @param surname фамилия
     * @param name имя
     * @param patronymic отчество
     * @param education образование
     * @param category категория
     * @param categoryId  идентификатор категории
     * @param dateOfStart  дата начала работы
     * @param accountnumber номер счета
     * @see Manager#Manager(int, String, String, String, String, Category, int, LocalDate, String)
     * @see Manager#Manager()
     **/
    public Manager(String surname, String name, String patronymic, String education, Category category, int categoryId, LocalDate dateOfStart, String accountnumber) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.education = education;
        this.category = category;
        this.categoryId = categoryId;
        this.dateOfStart = dateOfStart;
        this.accountNumber = accountnumber;
    }
    /**Пустой конструктор имеет вспомогательную роль при добавлении новой записи в базу
     * @see Manager#Manager(int, String, String, String, String, Category, int, LocalDate, String)
     * @see Manager#Manager(String, String, String, String, Category, int, LocalDate, String)
     * */
    public Manager() {
        this(0,null,null,null,null,null,0,null,null);
    }
    /**Функция для получения ФИО
     * @return Возвращает строку из {@link Manager#surname},{@link Manager#name},{@link Manager#patronymic} через пробел
     * **/
    public String getFIO(){
       return String.join(" ",surname,name,patronymic);
    }
}

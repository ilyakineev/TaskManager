package com.example.test.Entity;

/**
 * Работник.
 */
public interface Worker {

    /**
     * @return Идентификатор работника.
     */
    long getId();

    /**
     * @return Имя работника.
     */
    String getName();

    /**
     * @return Должность работника.
     */
    String getPosition();

    /**
     * @return Изображение работника.
     */
    String getAvatar();
}
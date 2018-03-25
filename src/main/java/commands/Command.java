package commands;

import exceptions.CommandException;
import messages.Message;
import server.Connection;

public interface Command {


    /**
     * Реализация паттерна Команда. Метод execute() вызывает соответствующую реализацию,
     * для запуска команды нужна сессия, чтобы можно было сгенерить ответ клиенту и провести валидацию
     * сессии.
     * @param connection - текущее соединение
     * @param message - сообщение для обработки
     * @throws CommandException - все исключения перебрасываются как CommandException
     */


    public Message execute(Connection connection, Message message) throws CommandException;
}

package net;

import messages.Message;

import java.io.IOException;


public interface ConnectionHandler {

    /**
     * Отправить сообщение.
     * Требуется обработать 2 типа ошибок
     *
     * @throws ProtocolException - ошибка протокола (не получилось кодировать/декодировать)
     * @throws IOException       - ошибка чтения/записи данных в сеть
     */
    void send(Message msg) throws ProtocolException, IOException;

    /**
     * Реакция на сообщение, пришедшее из сети
     */
    void onMessage(Message msg);

    /**
     * Молча (без проброса ошибок) закрываем соединение и освобождаем ресурсы
     */
    void close();
}

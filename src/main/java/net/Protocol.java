package net;

import messages.Message;


public interface Protocol {

    Message decode(byte[] bytes) throws ProtocolException;

    byte[] encode(Message msg) throws ProtocolException;
}

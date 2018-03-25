package net;

import messages.Message;

import java.io.*;


public class BinaryProtocol implements Protocol {

    @Override
    public Message decode(byte[] bytes) throws ProtocolException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            Message msg = (Message) ois.readObject();
            return msg;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    @Override
    public byte[] encode(Message msg) throws ProtocolException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(new ByteArrayOutputStream())) {
            out.writeObject(msg);
            byte[] objBytes = baos.toByteArray();
            return objBytes;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

package Server;

import Common.commands.CommandAbstract;
import Common.entities.CollectionManager;
import Common.requestSystem.Response;
import Common.requestSystem.Serializer;
import Server.exceptions.DisconnectInitException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class IOController {

    public static CommandAbstract getCommand(SocketChannel in) throws IOException, ClassNotFoundException {
        ByteBuffer readBuffer = ByteBuffer.allocate(4096);
        in.read(readBuffer);
        return Serializer.deserializeCommand(readBuffer.array());
    }

    public static Response buildResponse(CommandAbstract command, CollectionManager manager) throws DisconnectInitException {
        if (command.getName().equals("exit")) {
            throw new DisconnectInitException("Client initialize disconnect");
        }
        return (Response) command.execute(manager);
    }
}

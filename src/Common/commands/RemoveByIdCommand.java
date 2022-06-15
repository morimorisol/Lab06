package Common.commands;

import Common.entities.CollectionManager;
import Common.requestSystem.Response;

public class RemoveByIdCommand extends CommandAbstract {

    private final long id;

    public RemoveByIdCommand(long id) {
        super("remove_by_id", "удалить дракона с текущим значением id", 1);
        this.id = id;
    }

    @Override
    public Response execute(CollectionManager manager) {
        return new Response(manager.removeById(id));
    }
}

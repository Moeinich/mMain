package src.PastShadie.scripts.mMain.Assets;

public abstract class Task {

    public static String name;

    public Task() {
        super();
        String name = "Un-named";
    }

    public abstract boolean activate();

    public abstract void execute();
}
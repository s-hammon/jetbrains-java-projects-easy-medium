package tictactoe;

public class EasyComp implements Player {

    @Override
    public void move(Field field) {
        System.out.println(field);
        System.out.println("Making move level \"easy\"");

        moveRandomly(field);
    }

}
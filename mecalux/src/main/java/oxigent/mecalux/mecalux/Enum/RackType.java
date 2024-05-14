package oxigent.mecalux.mecalux.Enum;

public enum RackType {
    A, B, C, D;

    public char getTypeChar() {
        return name().charAt(0);
    }
}

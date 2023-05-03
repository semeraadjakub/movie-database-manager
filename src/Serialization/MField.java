package Serialization;
import static Serialization.SerializationWriter.*;

public class MField extends Base{
    private MField(byte type) {
        this.TYPE = type;
    }

    public byte getByte() {
        return byteVal;
    }

    public boolean getBoolean() {
        return boolVal;
    }

    public char getChar() {
        return charVal;
    }

    public short getShort() {
        return shortVal;
    }

    public int getInt() {
        return intVal;
    }

    public long getLong() {
        return longVal;
    }

    public float getFloat() {
        return floatVal;
    }

    public double getDouble() {
        return doubleVal;
    }

    public static MField Byte(String name, byte value) {
        MField field = new MField(Type.BYTE);
        field.setName(name);
        field.size += 1 + 1 + 1;
        field.byteVal = value;
        return field;
    }

    public static MField Boolean(String name, boolean value) {
        MField field = new MField(Type.BOOLEAN);
        field.setName(name);
        field.size += 1 + 1 + 1;
        field.boolVal = value;
        return field;
    }

    public static MField Char(String name, char value) {
        MField field = new MField(Type.CHAR);
        field.setName(name);
        field.size += 1 + 1 + 1;
        field.charVal = value;
        return field;
    }

    public static MField Short(String name, short value) {
        MField field = new MField(Type.SHORT);
        field.setName(name);
        field.size += 1 + 1 + 2;
        field.shortVal = value;
        return field;
    }

    public static MField Integer(String name, int value) {
        MField field = new MField(Type.INTEGER);
        field.setName(name);
        field.size += 1 + 1 + 4;
        field.intVal = value;
        return field;
    }

    public static MField Long(String name, long value) {
        MField field = new MField(Type.LONG);
        field.setName(name);
        field.size += 1 + 1 + 8;
        field.longVal = value;
        return field;
    }

    public static MField Float(String name, float value) {
        MField field = new MField(Type.BOOLEAN);
        field.setName(name);
        field.size += 1 + 1 + 4;
        field.floatVal = value;
        return field;
    }

    public static MField Double(String name, double value) {
        MField field = new MField(Type.DOUBLE);
        field.setName(name);
        field.size += 1 + 1 + 8;
        field.doubleVal = value;
        return field;
    }

    private final byte CONTAINER_TYPE = ContainerType.FIELD;
    public final byte TYPE;

    private byte byteVal;
    private boolean boolVal;
    private char charVal;
    private short shortVal;
    private int intVal;
    private long longVal;
    private float floatVal;
    private double doubleVal;

    public int serialize(int pointer, byte[] bytes) {
        pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
        pointer = writeBytes(pointer, bytes, TYPE);
        pointer = writeBytes(pointer, bytes, size);
        pointer = writeBytes(pointer, bytes, nameLength);
        pointer = writeBytes(pointer, bytes, name.getBytes());

        switch(TYPE) {
            case Type.BYTE:
                pointer = writeBytes(pointer, bytes, byteVal);
                break;
            case Type.BOOLEAN:
                pointer = writeBytes(pointer, bytes, boolVal);
                break;
            case Type.CHAR:
                pointer = writeBytes(pointer, bytes, charVal);
                break;
            case Type.SHORT:
                pointer = writeBytes(pointer, bytes, shortVal);
                break;
            case Type.INTEGER:
                pointer = writeBytes(pointer, bytes, intVal);
                break;
            case Type.LONG:
                pointer = writeBytes(pointer, bytes, longVal);
                break;
            case Type.FLOAT:
                pointer = writeBytes(pointer, bytes, floatVal);
                break;
            case Type.DOUBLE:
                pointer = writeBytes(pointer, bytes, doubleVal);
                break;
        }
        return pointer;
    }

    public int serializeAsArrayElement(int pointer, byte[] bytes) {
        switch(TYPE) {
            case Type.BYTE:
                pointer = writeBytes(pointer, bytes, byteVal);
                break;
            case Type.BOOLEAN:
                pointer = writeBytes(pointer, bytes, boolVal);
                break;
            case Type.CHAR:
                pointer = writeBytes(pointer, bytes, charVal);
                break;
            case Type.SHORT:
                pointer = writeBytes(pointer, bytes, shortVal);
                break;
            case Type.INTEGER:
                pointer = writeBytes(pointer, bytes, intVal);
                break;
            case Type.LONG:
                pointer = writeBytes(pointer, bytes, longVal);
                break;
            case Type.FLOAT:
                pointer = writeBytes(pointer, bytes, floatVal);
                break;
            case Type.DOUBLE:
                pointer = writeBytes(pointer, bytes, doubleVal);
                break;
        }
        return pointer;
    }

    public static MField Deserialize(int pointer, byte[] bytes) {
        pointer++;
        byte type = readByte(pointer, bytes);
        pointer++;
        MField field = new MField(type);
        field.size = readInt(pointer, bytes);
        pointer += 4;
        field.nameLength = readShort(pointer, bytes);
        pointer += 2;
        field.name = readString(pointer, bytes, field.nameLength);
        pointer += field.nameLength;

        switch(type) {
            case Type.BYTE:
                field.byteVal = readByte(pointer, bytes);
                pointer++;
                break;
            case Type.BOOLEAN:
                field.boolVal = readBoolean(pointer, bytes);
                pointer++;
                break;
            case Type.CHAR:
                field.charVal = readChar(pointer, bytes);
                pointer++;
                break;
            case Type.SHORT:
                field.shortVal = readShort(pointer, bytes);
                pointer += 2;
                break;
            case Type.INTEGER:
                field.intVal = readInt(pointer, bytes);
                pointer += 4;
                break;
            case Type.LONG:
                field.longVal = readLong(pointer, bytes);
                pointer += 8;
                break;
            case Type.FLOAT:
                field.floatVal = readFloat(pointer, bytes);
                pointer += 4;
                break;
            case Type.DOUBLE:
                field.doubleVal = readDouble(pointer, bytes);
                pointer += 8;
                break;
        }

        return field;
    }
}

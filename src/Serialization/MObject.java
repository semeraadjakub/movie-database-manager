package Serialization;
import static Serialization.SerializationWriter.*;
import java.util.ArrayList;
import java.util.List;
public class MObject extends Base{
    private final byte CONTAINER_TYPE = ContainerType.OBJECT;

    private List<MField> fields = new ArrayList<MField>();
    private List<MArray> arrays = new ArrayList<MArray>();
    private List<MString> strings = new ArrayList<MString>();
    private int arrayCount, fieldCount, stringCount;

    public MObject(String name) {
        setName(name);
        arrays = new ArrayList<MArray>();
        fields = new ArrayList<MField>();
        strings = new ArrayList<MString>();
        size += 1 + 4 + 4 + 4;
    }

    public MField findField(String fieldName) {
        for(int i = 0; i < fields.size(); i++)
            if(fields.get(i).name.equals(fieldName))
                return fields.get(i);
        return null;
    }

    public MString findString(String stringName) {
        for(int i = 0; i < strings.size(); i++)
            if(strings.get(i).name.equals(stringName))
                return strings.get(i);
        return null;
    }

    public MArray findArray(String arrayName) {
        for(int i = 0; i < arrays.size(); i++)
            if(arrays.get(i).name.equals(arrayName))
                return arrays.get(i);
        return null;
    }

    public void add(MField field) {
        fields.add(field);
        size += field.size;
        fieldCount++;
    }

    public void add(MArray array) {
        arrays.add(array);
        size += array.size;
        arrayCount++;
    }

    public void add(MString string) {
        strings.add(string);
        size += string.size;
        stringCount++;
    }

    public int serialize(int pointer, byte[] bytes) {

        pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
        pointer = writeBytes(pointer, bytes, size);
        pointer = writeBytes(pointer, bytes, nameLength);
        pointer = writeBytes(pointer, bytes, name.getBytes());

        pointer = writeBytes(pointer, bytes, fieldCount);

        for(int i = 0; i < fieldCount; i++) {
            MField field = fields.get(i);
            pointer = field.serialize(pointer, bytes);
        }

        pointer = writeBytes(pointer, bytes, arrayCount);

        for(int i = 0; i < arrayCount; i++) {
            MArray array = arrays.get(i);
            pointer = array.serialize(pointer, bytes);
        }

        pointer = writeBytes(pointer, bytes, stringCount);

        for(int i = 0; i < stringCount; i++) {
            MString string = strings.get(i);
            pointer = string.serialize(pointer, bytes);
        }

        return pointer;
    }

    public static MObject Deserialize(int pointer, byte[] bytes) {
        MObject obj = new MObject("");
        pointer += 1;
        obj.size = readInt(pointer, bytes);
        pointer += 4;
        obj.nameLength = readShort(pointer, bytes);
        pointer += 2;
        obj.name = readString(pointer, bytes, obj.nameLength);
        pointer += obj.nameLength;
        obj.fieldCount = readInt(pointer, bytes);
        pointer += 4;

        for(int i = 0; i < obj.fieldCount; i++) {
            MField field = MField.Deserialize(pointer, bytes);
            pointer += field.size;
            obj.fields.add(field);
        }

        obj.arrayCount = readInt(pointer, bytes);
        pointer += 4;

        for(int i = 0; i < obj.arrayCount; i++) {
            MArray array = MArray.Deserialize(pointer, bytes);
            pointer += array.size;
            obj.arrays.add(array);
        }

        obj.stringCount = readInt(pointer, bytes);
        pointer += 4;

        for(int i = 0; i < obj.stringCount; i++) {
            MString string = MString.Deserialize(pointer, bytes);
            pointer += string.size;
            obj.strings.add(string);
        }

        return obj;
    }
}

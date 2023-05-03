package Serialization;
import java.nio.ByteBuffer;
public class SerializationWriter {
    public static int writeBytes(int pointer, byte[] bytes, byte value) {
        bytes[pointer++] = value;
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, byte[] values) {
        for(int i = 0 ; i < values.length; i++) {
            bytes[pointer++] = values[i];
        }
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, char value) {
        bytes[pointer++] = (byte)value;
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, boolean value) {
        bytes[pointer++] = (value == true ? (byte)1 : (byte)0);
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, short value) {
        bytes[pointer++] = (byte)((value >> 8) & 0xff);
        bytes[pointer++] = (byte)((value) & 0xff);
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, int value) {
        bytes[pointer++] = (byte)((value >> 24) & 0xff);
        bytes[pointer++] = (byte)((value >> 16) & 0xff);
        bytes[pointer++] = (byte)((value >> 8) & 0xff);
        bytes[pointer++] = (byte)((value) & 0xff);
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, long value) {
        bytes[pointer++] = (byte)((value >> 56) & 0xff);
        bytes[pointer++] = (byte)((value >> 48) & 0xff);
        bytes[pointer++] = (byte)((value >> 40) & 0xff);
        bytes[pointer++] = (byte)((value >> 32) & 0xff);
        bytes[pointer++] = (byte)((value >> 24) & 0xff);
        bytes[pointer++] = (byte)((value >> 16) & 0xff);
        bytes[pointer++] = (byte)((value >> 8) & 0xff);
        bytes[pointer++] = (byte)((value) & 0xff);
        return pointer;
    }

    public static int writeBytes(int pointer, byte[] bytes, float val) {
        int value = Float.floatToIntBits(val);
        return writeBytes(pointer, bytes, value);
    }

    public static int writeBytes(int pointer, byte[] bytes, double val) {
        long value = Double.doubleToLongBits(val);
        return writeBytes(pointer, bytes, value);
    }

    //reader

    public static byte readByte(int pointer, byte[] bytes) {
        return bytes[pointer];
    }

    public static boolean readBoolean(int pointer, byte[] bytes) {
        return bytes[pointer] != 0;
    }

    public static char readChar(int pointer, byte[] bytes) {
        return (char)bytes[pointer];
    }

    public static short readShort(int pointer, byte[] bytes) {
        return ByteBuffer.wrap(bytes, pointer, 2).getShort();
    }

    public static int readInt(int pointer, byte[] bytes) {
        return ByteBuffer.wrap(bytes, pointer, 4).getInt();
        //return (bytes[pointer + 1] << 24) | (bytes[pointer + 2] << 16) | (bytes[pointer + 3] << 8) | (bytes[pointer + 4]);
    }

    public static long readLong(int pointer, byte[] bytes) {
        return ByteBuffer.wrap(bytes, pointer, 8).getLong();
    }

    public static float readFloat(int pointer, byte[] bytes) {
        return Float.intBitsToFloat(readInt(pointer, bytes));
    }

    public static double readDouble(int pointer, byte[] bytes) {
        return Double.longBitsToDouble(readLong(pointer, bytes));
    }

    public static void readBytes(int pointer, byte[] bytes, byte[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = readByte(pointer, bytes);
            pointer++;
        }
    }

    public static void readBooleans(int pointer, byte[] bytes, boolean[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = readBoolean(pointer, bytes);
            pointer++;
        }
    }

    public static void readChars(int pointer, byte[] bytes, char[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = readChar(pointer, bytes);
            pointer++;
        }
    }

    public static void readShorts(int pointer, byte[] bytes, short[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = readShort(pointer, bytes);
            pointer += 2;
        }
    }

    public static void readInts(int pointer, byte[] bytes, int[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = readInt(pointer, bytes);
            pointer += 4;
        }
    }

    public static void readLongs(int pointer, byte[] bytes, long[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = readLong(pointer, bytes);
            pointer += 8;
        }
    }

    public static void readFloats(int pointer, byte[] bytes, float[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = Float.intBitsToFloat(readInt(pointer, bytes));
            pointer += 4;
        }
    }

    public static void readDoubles(int pointer, byte[] bytes, double[] dst) {
        for(int i = 0; i < dst.length; i++) {
            dst[i] = Double.longBitsToDouble(readLong(pointer, bytes));
            pointer += 8;
        }
    }

    public static String readString(int pointer, byte[] bytes, int length) {
        return new String(bytes, pointer, length);
    }
}

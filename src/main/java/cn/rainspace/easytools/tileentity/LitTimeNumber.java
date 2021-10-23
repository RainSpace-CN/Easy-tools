package cn.rainspace.easytools.tileentity;

import net.minecraft.util.IIntArray;


public class LitTimeNumber implements IIntArray {
    int array[]=new int[2];
    @Override
    public int get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, int value) {
        array[index]=value;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

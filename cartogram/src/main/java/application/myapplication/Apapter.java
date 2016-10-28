package application.myapplication;


/**
 * 类 名 称  ： Apapter.class
 * 作    者 ：  czg
 * 日    期 ：  2016/9/30.
 * 作    用 ： 在这里写一句话描述作用
 */
public class Apapter extends PkCircleViewAdapter {
    private String[] colors= new String[]{"#f42f45","#2f7ef4","#0fe145","#f9be1d"};
    private int[] sizes;

    public Apapter() {
        sizes=new int[]{10,30,10,10};
    }

    public void setSize(int i, int size) {
        sizes[i]=size;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fanned getFanned(Fanned fanned, int postiton) {
        fanned.setColor(colors[postiton]);
        fanned.setSize(sizes[postiton]);
        return fanned;
    }

}

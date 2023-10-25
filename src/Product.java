import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

enum ProductType {              // ��ǰ ������(Enum)
    /*
        Enum �������: ������ �� ��������, ������(enum Ÿ���� ���� ���), �ڵ� �ߺ� ����, Ư�� �� ���� ǥ��
     */
    RCMND(1, 1, "������õ"),
    MY_SALAD(2, 1, "������ ������"),
    DRINK(3, 1, "����"),
    SIDE(4, 1, "���̵�"),

    S_BASE(5, 2, "���̽�"),
    S_MAIN(6, 2, "��������"),
    S_SIDE(7, 2, "���̵�����"),
    S_SOURCE(8, 2, "�ҽ�"),

    CANCEL(-1, -1, "���");

    private int index;
    private int depth;
    private String name;

    ProductType(int index, int depth, String name) {
        this.index = index;
        this.depth = depth;
        this.name = name;
    }


    // getter
    public int getIndex() {
        return index;
    }
    public int getDepth() {
        return depth;
    }
    public String getName() {
        return name;
    }
}



public class Product implements Serializable {

    private static final long serialVersionUID = 4570189582182369883L;
    //���丮 ������ ��ắ�� ����
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int p_checkNumber;  // ���й�ȣ
    private int p_material;     // �з���ȣ
    private String p_name;      // �̸�
    private String p_unit;      // ����
    private int p_count;        // ����
    private int p_calorie;      // Į�θ�
    private int p_stock;        // �������
    private int p_price;        // �ݾ�
    private ProductType type;   // �з���ȣ�� ���� Ÿ��
    private int p_limitCount;   // ��ٱ��� ���� ���ð��� ����
    private boolean p_limitFlag ; // ��ٱ��� ���� ���� üũ.
                                  // false : ����Ϸ����  �� ����� �Ǹ� ��� ���� �ʿ�,
                                  // true  : ����̿Ϸ���� �� ����� �Ǹ� ��� ���� ���ʿ�
    private boolean saleflag;   // �Ǹſ���

    public Product(int p_checkNumber, ProductType type, int p_material, String p_name, String p_unit, int p_count, int p_calorie, int p_stock, int p_price, boolean Saleflag) {
        this.p_checkNumber = p_checkNumber;
        this.type = type;
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
        this.saleflag = Saleflag;
    }

    // ������
    public Product(int p_checkNumber, int p_material, String p_name, String p_unit, int p_count, int p_calorie, int p_stock, int p_price) {
        this.p_checkNumber = p_checkNumber;
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
    }

    // ������
    public Product() {
        this(0, 0, "", "", 0, 0, 0, 0);
    }

    //getter setter
    public ProductType getType() {
        return type;
    }
    public int getP_checkNumber() {
        return p_checkNumber;
    }
    public void setP_checkNumber(int p_checkNumber) {
        this.p_checkNumber = p_checkNumber;
    }
    public int getP_material() {
        return p_material;
    }
    public void setP_material(int p_material) {
        this.p_material = p_material;
    }
    public String getP_name() {
        return p_name;
    }
    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
    public String getP_unit() {
        return p_unit;
    }
    public void setP_unit(String p_unit) {
        this.p_unit = p_unit;
    }
    public int getP_count() {
        return p_count;
    }
    public void setP_count(int p_count) {
        this.p_count = p_count;
    }
    public int getP_calorie() {
        return p_calorie;
    }
    public void setP_calorie(int p_calorie) {
        this.p_calorie = p_calorie;
    }
    public int getP_stock() {
        return p_stock;
    }
    public void setP_stock(int p_stock) {
        this.p_stock = p_stock;
    }
    public int getP_price() {
        return p_price;
    }
    public void setP_price(int p_price) {
        this.p_price = p_price;
    }
    public int getP_limitCount() {
        return p_limitCount;
    }
    public void setP_limitCount(int p_limitCount) {
        this.p_limitCount = p_limitCount;
    }
    public boolean getP_limitFlag() { return p_limitFlag; }
    public void setP_limitFlag(boolean p_limitFlag) { this.p_limitFlag = p_limitFlag; }
    public boolean getSaleflag() {
        return saleflag;
    }
    public void setSaleflag(boolean Saleflag) {
        this.saleflag = Saleflag;
    }

    @Override
    public String toString() {
        return "Product{" +
//                "appDir='" + appDir + '\'' +
                ", br=" + br +
                ", p_checkNumber=" + p_checkNumber +
                ", p_material=" + p_material +
                ", p_name='" + p_name + '\'' +
                ", p_unit='" + p_unit + '\'' +
                ", p_count=" + p_count +
                ", p_calorie=" + p_calorie +
                ", p_stock=" + p_stock +
                ", p_price=" + p_price +
                ", type=" + type +
                ", p_limitCount=" + p_limitCount +
                '}';
    }

}
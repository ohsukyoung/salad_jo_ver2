import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

enum ProductType {              // 제품 열거혐(Enum)
    /*
        Enum 사용이유: 가독성 및 유지보수, 안전성(enum 타입의 값만 허용), 코드 중복 방지, 특정 값 집합 표현
     */
    RCMND(1, 1, "사장추천"),
    MY_SALAD(2, 1, "나만의 샐러드"),
    DRINK(3, 1, "음료"),
    SIDE(4, 1, "사이드"),

    S_BASE(5, 2, "베이스"),
    S_MAIN(6, 2, "메인토핑"),
    S_SIDE(7, 2, "사이드토핑"),
    S_SOURCE(8, 2, "소스"),

    CANCEL(-1, -1, "취소");

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
    //디렉토리 생성과 재료변수 선언
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int p_checkNumber;  // 구분번호
    private int p_material;     // 분류번호
    private String p_name;      // 이름
    private String p_unit;      // 단위
    private int p_count;        // 개수
    private int p_calorie;      // 칼로리
    private int p_stock;        // 적정재고
    private int p_price;        // 금액
    private ProductType type;   // 분류번호에 따른 타입
    private int p_limitCount;   // 장바구니 연동 선택가능 수량
    private boolean p_limitFlag ; // 장바구니 결재 여부 체크.
                                  // false : 결재완료상태  로 사용자 판매 재고 셋팅 필요,
                                  // true  : 결재미완료상태 로 사용자 판매 재고 셋팅 불필요
    private boolean saleflag;   // 판매여부

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

    // 생성자
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

    // 생성자
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
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

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



public class Product implements Serializable, Impl_admin {

    private static final long serialVersionUID = 4570189582182369883L;
    //디렉토리 생성과 재료변수 선언
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int p_checkNumber;
    private int p_material;
    private String p_name;
    private String p_unit;
    private int p_count;
    private int p_calorie;
    private int p_stock;
    private int p_price;
    private ProductType type;
    private int p_limitCount;
    private boolean Saleflag;


    public Product(int p_checkNumber, ProductType type,int p_material, String p_name, String p_unit, int p_count, int p_calorie, int p_stock, int p_price, boolean Saleflag) {
        this.p_checkNumber = p_checkNumber;
        this.type = type;
        this.p_material = p_material;
        this.p_name = p_name;
        this.p_unit = p_unit;
        this.p_count = p_count;
        this.p_calorie = p_calorie;
        this.p_stock = p_stock;
        this.p_price = p_price;
        this.Saleflag = Saleflag;
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
    public Product(){ this(0, 0, "", "", 0, 0, 0, 0); }

    //getter setter
    public ProductType getType() { return type;}
    public int getP_checkNumber() { return p_checkNumber; }
    public void setP_checkNumber(int p_checkNumber) { this.p_checkNumber = p_checkNumber; }
    public int getP_material() { return p_material; }
    public void setP_material(int p_material) { this.p_material = p_material; }
    public String getP_name() { return p_name; }
    public void setP_name(String p_name) { this.p_name = p_name; }
    public String getP_unit() { return p_unit; }
    public void setP_unit(String p_unit) { this.p_unit = p_unit; }
    public int getP_count() { return p_count; }
    public void setP_count(int p_count) { this.p_count = p_count; }
    public int getP_calorie() { return p_calorie; }
    public void setP_calorie(int p_calorie) { this.p_calorie = p_calorie; }
    public int getP_stock() { return p_stock; }
    public void setP_stock(int p_stock) { this.p_stock = p_stock; }
    public int getP_price() { return p_price; }
    public void setP_price(int p_price) { this.p_price = p_price; }
    public int getP_limitCount() { return p_limitCount; }
    public void setP_limitCount(int p_limitCount) { this.p_limitCount = p_limitCount; }
    public boolean getSaleflag() { return Saleflag; }
    public void setSaleflag(boolean Saleflag) { this.Saleflag = Saleflag; }

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

    @Override
    public void ad_print() throws IOException, ClassNotFoundException {

        List<Product> product = CacheData.allProductList;



        System.out.println("\n\t[ 1. 재료정보 출력 ]===============");
        System.out.println("\t1. 전체 재료정보 출력  \n\t2. 선택 재료정보 출력 ");
        System.out.println("\t==============================");

        int seletCheckNnumber=0;
        int materialNumber=0;
        while (true)
        {
            try{
                System.out.print("\t▶ 선택할 항목의 숫자 입력 : ");
                seletCheckNnumber = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
                continue;
            }

            switch (seletCheckNnumber)
            {
                case 1 :
                    System.out.println("\n\t[ 1. 전체 재료정보 출력 ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||%5s ||\n",
                            "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                    System.out.println("\t==========================================================================================================");

                    // Iterator 활용하여 출력
                    Iterator<Product> itList = product.iterator();
                    while (itList.hasNext())
                    {
                        Product itS = itList.next();
                        String saleText = "";
                        saleText = itS.getSaleflag()? "판매 O": "판매 X";
                        System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                case 2 :
                    System.out.println("\n\t[ 2. 선택 재료정보 출력 ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                    System.out.println("\t==========================================================================================================");
                    System.out.println("\t*** [분류번호 안내] 1: 사장추천, 3:음료, 4:사이드, 5:베이스, 6:메인토핑, 7:사이드토핑, 8:소스");
                    while (true) {
                        try {
                            System.out.print("\t▶ 분류번호 입력 : ");
                            materialNumber = Integer.parseInt(br.readLine());

                            boolean found = false; // found 변수 초기화

                            for (int i = 0; i < product.size(); i++) {
                                if (materialNumber == product.get(i).getP_material()) {
                                    found = true;
                                    break; // 해당 분류번호를 찾았으므로 루프 종료
                                }
                            }

                            if (found) {
                                break; // 올바른 분류번호를 찾았으므로 루프 종료
                            } else {
                                System.out.println("\t▶ 구분번호가 존재하지 않습니다.");
                            }
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
                        }

                    }


                    // Iterator 활용하여 선택한 분류번호에 해당하는 재료정보 출력
                    Iterator<Product> itList1 = product.iterator();
                    while (itList1.hasNext())
                    {
                        Product itS = itList1.next();
                        if (itS.getP_material() == materialNumber)
                        {
                            String saleText = "";
                            saleText = itS.getSaleflag()? "판매 O": "판매 X";
                            System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||%5s ||\n", itS.getP_checkNumber(),
                                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                    itS.getP_stock(), itS.getP_price(), saleText);
                        }
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                default:
                    System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
                    continue;

            }

        }

    }


    @Override
    public void ad_add() throws IOException,ClassNotFoundException {

        //자료구조 생성
        List<Product> list1 = CacheData.allProductList;

        boolean shouldExit = false;
        System.out.println("\n\t[ 2. 신규재료 등록 ]");
        System.out.println("\t==========================================================================================================");
        System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
        System.out.println("\t==========================================================================================================");
		System.out.println("\t*** [분류번호 안내] 1: 사장추천, 3:음료, 4:사이드, 5:베이스, 6:메인토핑, 7:사이드토핑, 8:소스");

        System.out.print("\t▶ 재료정보 입력 (스페이스로 구분): ");
        String input = br.readLine();

        // 토크나이저로 스페이스로 끊어주기
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        if (tokenizer.countTokens() != 8) {
            System.out.println("\t[!] 입력한 항목의 갯수가 맞지 않습니다.");
            return;
        }

        try{

            int p_checkNumber = Integer.parseInt(tokenizer.nextToken());
            int p_material = Integer.parseInt(tokenizer.nextToken());

            ProductTypeChange productTypeChange = new ProductTypeChange();
            ProductType productType= productTypeChange.ProductTypeChange(p_material);

            String p_name = tokenizer.nextToken();
            String p_unit = tokenizer.nextToken();
            int p_count = Integer.parseInt(tokenizer.nextToken());
            int p_calorie = Integer.parseInt(tokenizer.nextToken());
            int p_stock = Integer.parseInt(tokenizer.nextToken());
            int p_price = Integer.parseInt(tokenizer.nextToken());

            List<Product> existingList;
            existingList = CacheData.allProductList;

            boolean m = false;
            for (int i = 0; i < existingList.size(); i++){
                if (p_checkNumber == existingList.get(i).getP_checkNumber()){
                    m = true;
                    break;
                }
            }
            if (m){
                System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                return;
            }
            else {
                System.out.print("\t▶ 저장하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    Product product = new Product(p_checkNumber, productType, p_material, p_name, p_unit, p_count, p_calorie, p_stock, p_price, Saleflag);
                    list1.add(product);
                    // 직렬화 주석 231018
//                    FileMg f = new FileMg();
//                    f.list1FileOut();
//                    f.list3FileOut();

                    System.out.println("\t「 저장되었습니다. 」");
                } else {
                    System.out.println("\t[!] 올바른 값을 입력하시오.");
                    return;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("\t[!] 잘못된 숫자 형식입니다. 다시 입력하세요.");
        }
        KioskMg.productflag = false;
    }



    @Override
    public void ad_modify() throws IOException, ClassNotFoundException {
        List<Product> product = CacheData.allProductList;
        List<Product> list3 = CacheData.list3;

        System.out.println("\n\t[ 3. 재료정보 변경 ]");
        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
        int pointNumber;

        try {
            pointNumber = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("\t[!] 올바른 구분번호를 입력하세요.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < product.size(); i++) {
            if (pointNumber == product.get(i).getP_checkNumber()) {
                found = true;
                Product itS = product.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "판매 O": "판매 X";
				System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
                System.out.println("\t==========================================================================================================");

                System.out.println("\n\t[ 변경할 내용 선택 ]-----------------------------------------------------");
                System.out.println("\t1.구분번호 2. 분류번호 3. 이름 4. 단위 5. 개수 6. 칼로리 7. 적정재고 8. 금액");
                System.out.println("\t----------------------------------------------------------------------");
                System.out.println();

                while (true) {
                    System.out.print("\t▶ 변경할 내용의 숫자 입력 (0: 변경 완료) : ");
                    int choice;

                    try {
                        choice = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("\t[!] 올바른 선택을 입력하세요.");
                        return;
                    }

                    if (choice == 0) {
                        // 변경 완료 시, 변경된 정보를 저장
                        // 직렬화 주석 231018
//                        FileMg f = new FileMg();
//                        f.list1FileOut();
//                        f.list3FileOut();
                        System.out.println("\t「 수정되었습니다. 」");
                        return; // 메소드를 빠져나갑니다.
                    } else if (choice < 1 || choice > 8) {
                        System.out.println("\t[!] 잘못된 선택입니다. 다시 선택하세요.");
                    } else {
                        System.out.print("\t▶ 새로운 값 입력 : ");
                        String newValue = br.readLine();
                        try {
                            switch (choice) {
                                case 1:
                                    int newCheckNumber = Integer.parseInt(newValue);
                                    itS.setP_checkNumber(newCheckNumber);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_checkNumber(newCheckNumber);
                                        }
                                    }
                                    break;
                                case 2:
                                    int newMaterial = Integer.parseInt(newValue);
                                    itS.setP_material(newMaterial);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_material(newMaterial);
                                        }
                                    }
                                    break;
                                case 3:
                                    itS.setP_name(newValue);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_name(newValue);
                                        }
                                    }
                                    break;
                                case 4:
                                    itS.setP_unit(newValue);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_unit(newValue);
                                        }
                                    }
                                    break;
                                case 5:
                                    int newCount = Integer.parseInt(newValue);
                                    itS.setP_count(newCount);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_count(newCount);
                                        }
                                    }
                                    break;
                                case 6:
                                    int newCalorie = Integer.parseInt(newValue);
                                    itS.setP_calorie(newCalorie);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_calorie(newCalorie);
                                        }
                                    }
                                    break;
                                case 7:
                                    int newStock = Integer.parseInt(newValue);
                                    itS.setP_stock(newStock);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_stock(newStock);
                                        }
                                    }
                                    break;
                                case 8:
                                    int newPrice = Integer.parseInt(newValue);
                                    itS.setP_price(newPrice);
                                    for (Product p : list3) {
                                        if (p.getP_checkNumber() == pointNumber) {
                                            p.setP_price(newPrice);
                                        }
                                    }
                                    break;

                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\t[!] 올바른 값을 입력하세요.");
                        }
                    }
                }
            }
        }

        if (!found) {
            System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
        }

        KioskMg.productflag = false;
    }



    @Override
    public void ad_delete() throws IOException, ClassNotFoundException {
        List<Product> product = CacheData.allProductList;
        List<Product> list3 = CacheData.list3;

        System.out.println("\n\t[ 4. 재료정보 삭제 ]");
        System.out.print("\t▶ 작업할 대상의 구분번호 입력 (뒤로가기:0) : ");
        int pointNumber;


        try {
            pointNumber = Integer.parseInt(br.readLine());
            if(pointNumber==0)
            {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] 올바른 구분번호를 입력하세요.");
            return;
        }

        boolean found = false;

        int deleteIndex = -1; // 삭제할 아이템의 인덱스 초기화

        for (int i = 0; i < product.size(); i++) {
            if (pointNumber == product.get(i).getP_checkNumber()) {
                found = true;
                Iterator<Product> itList;
                itList = product.iterator();
                Product itS = product.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "판매 O": "판매 X";
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
				System.out.println("\t==========================================================================================================");
                System.out.println();
                System.out.println();
                System.out.print("\t▶ 삭제하시겠습니까? (Y/N) : ");
                char x = br.readLine().charAt(0);

                if (x == 'Y' || x == 'y') {
                    deleteIndex = i; // 삭제 대상 제품의 인덱스 설정
                    for (Iterator<Product> iterator = list3.iterator(); iterator.hasNext(); ) {
                        Product p = iterator.next();
                        if (p.getP_checkNumber() == pointNumber) {
                            iterator.remove(); // list3에서 안전하게 제거
                        }
                    }
                    break;
                } else if (x == 'N' || x == 'n') {
                    return; // 'N'을 입력하면 메소드를 빠져나옵니다.
                } else {
                    System.out.println("\t[!] 올바른 값을 입력하시오.");
                    return;
                }
            }
        }

        if (!found) {
            System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
            return; // 일치하지 않으면 삭제 작업을 하지 않고 종료
        }

        // 삭제 대상 재료가 설정된 경우에만 삭제 수행
        if (deleteIndex != -1) {
            product.remove(deleteIndex);

            // 직렬화 주석 231018
//            FileMg f = new FileMg();
//            f.list1FileOut();
//            f.list3FileOut();
            System.out.println("\t「 삭제되었습니다. 」");
        }

        KioskMg.productflag = false;
    }

}
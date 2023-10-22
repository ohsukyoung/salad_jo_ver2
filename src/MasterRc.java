import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;

class MasterRc implements Serializable, Impl_admin
{
    private static final long serialVersionUID = -2026629343147755130L;
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int r_checkNumber;
    private String r_name;
    private int r_totalcalorie;
    private int r_price;
    private double r_discount;
    private List<Product> r_products; // 재료 목록
    private int[] r_details = new int[3]; // 재료 목록 상세
    private ProductType type;
    private int r_count;
    private int r_limitCount;
    private boolean Saleflag;

    String SaleTrue = CacheData.SaleTrue;
    String SaleFalse = CacheData.SaleFalse;

    public ProductType getType() { return type; }

    int materialDetailCount = 3;

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, List<Product> r_products, double r_discount, int r_count, boolean Saleflag)
    {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this.r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_products = r_products;
        this.r_discount = r_discount;
        this.r_count = r_count;
        this.Saleflag = Saleflag;
    }

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, int[] r_details, double r_discount, int r_count, boolean Saleflag) {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this.r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_details = r_details;
        this.r_discount = r_discount;
        this.r_count = r_count;
        this.Saleflag = Saleflag;
    }

    public MasterRc() {
        this(0, ProductType.RCMND, "", 0, 0, new ArrayList<>(), 0, 0, false);
    }

    public int getR_checkNumber() {
        return r_checkNumber;
    }

    public void setR_checkNumber(int r_checkNumber) {
        this.r_checkNumber = r_checkNumber;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public int getR_totalcalorie() {
        return r_totalcalorie;
    }

    public void setR_totalcalorie(int r_totalcalorie) {
        this.r_totalcalorie = r_totalcalorie;
    }

    public int getR_price() {
        return r_price;
    }

    public void setR_price(int r_price) {
        this.r_price = r_price;
    }

    public List<Product> getR_products() {
        return r_products;
    }

    public double getR_discount() {
        return r_discount;
    }

    public void setR_discount(double r_discount) {
        this.r_discount = r_discount;
    }

    public int getR_count() {
        return r_count;
    }

    public void setR_count(int r_count) {
        this.r_count = r_count;
    }

    public void setR_products(List<Product> r_products) {
        this.r_products = r_products;
    }

    public int getR_limitCount() {
        return r_limitCount;
    }

    public void setR_limitCount(int r_limitCount) {
        this.r_limitCount = r_limitCount;
    }

    public boolean getSaleflag() {
        return Saleflag;
    }

    public void setSaleflag(boolean Saleflag) {
        this.Saleflag = Saleflag;
    }

    public int[] getR_details() {
        return r_details;
    }

    public void setR_details(int r_details) {
        this.r_details = new int[]{r_details};
    }

    // MasterRc 객체 내의 재료의 총 칼로리 계산
    private int calculateTotalCalorie_01(int[] detailNum) {
        int totalCalorie = 0;
        for (int i = 0; i < detailNum.length; i++) {
            for (Product p1 : CacheData.allProductList) {
                if (i == p1.getP_checkNumber()){
                    totalCalorie += p1.getP_calorie();
                }
            }
        }
        r_totalcalorie = totalCalorie;
        return totalCalorie;
    }

    // MasterRc 객체 내의 재료의 총 금액 계산
    private int calculateTotalPrice_01(int[] detailNum) {
        int totalPrice = 0;
        for (int i = 0; i < detailNum.length; i++) {
            for (Product p1 : CacheData.allProductList) {
                if (i == p1.getP_checkNumber()){
                    totalPrice += p1.getP_price();
                }
            }
        }
        return totalPrice;
    }

    // MasterRc 객체 내의 재고 개수 계산
    public int calculateMinCount_01(int[] detailNum) {
        int minCount = 10000;
        for (int i = 0; i < detailNum.length; i++) {
            for (Product p1 : CacheData.allProductList) {
                if (detailNum[i] == p1.getP_checkNumber()){
                    if (minCount > p1.getP_count())
                        minCount = p1.getP_count();
                }

            }
        }
        return minCount;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public final int E_PRINT = 1;
    public final int E_ADD = 2;
    public final int E_MOD = 3;
    public final int E_DEL = 4;
    public final int E_EXIT = 5;


    private static Integer sel;				         //-- 선택 값


    static
    {
        // 사용자 입력값 초기화
        sel = 1;
    }

    public void menuDisp()
    {
        System.out.println("\n\t[ 사장추천 관리 메뉴 선택 ]===========");
        System.out.println("\t1. 추천조합 출력");
        System.out.println("\t2. 추천조합 등록");
        System.out.println("\t3. 추천조합 정보 변경");
        System.out.println("\t4. 추천조합 정보 삭제");
        System.out.println("\t5. 관리자 메뉴 화면으로 이동");
        System.out.println("\t=================================");
        System.out.print("\t▶ 메뉴 선택(1~5) : ");
    }

    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // 메뉴 실행에 따른 기능 호출 메소드
    public void menuRun() throws IOException, ClassNotFoundException
    {
        switch (sel)
        {
            case E_PRINT : ad_print(); break;
            case E_ADD : ad_add(); break;
            case E_MOD : ad_modify(); break;
            case E_DEL : ad_delete(); break;
            case E_EXIT : exit(); break;
            default : System.out.print("\t[!] 메뉴 선택 오류");
        }
    }

    @Override
    public void ad_print() {
        List<MasterRc> masterProductList = CacheData.masterRcList;

        System.out.println("\n\t[ 1. 사장 추천 조합 출력 ]");
        System.out.println("\t=============================================================================================================");
        System.out.printf("\t|| %5s || %-9s || %5s || %5s || %5s || %5s || %5s ||\n",
                "구분번호", "이름", "칼로리", "금액", "개수", "판매여부", "재료(" + materialDetailCount + ")");
        System.out.println("\t=============================================================================================================");

        // MasterRc 객체를 출력
        for (MasterRc masterRc : masterProductList) {
            // 판매정보 출력
            String saleText = "";
            saleText = masterRc.getSaleflag() ? SaleTrue : SaleFalse;

            // 수량 현행화
            int calTotalMin = calculateMinCount_01(masterRc.r_details);
            masterRc.setR_count(calTotalMin);

            System.out.printf("\t|| %5d || %-9s || %5d || %5d || %5d || %5s ",
                    masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                    , masterRc.getR_price(), calTotalMin, saleText);

            // 상세재료 출력
            String materialDetail = "";
            for (int i = 0; i < masterRc.getR_details().length; i++) {
                for (Product p1 : CacheData.allProductList) {
                    if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                        if (i != 0){
                            materialDetail += ", ";
                        }
                        String materialItem = p1.getP_name();
                        materialDetail += materialItem;
                    }
                }
            }
            System.out.printf("|| %-12s\n", materialDetail);

            // 재료정보 출력
            for (int i = 0; i < masterRc.getR_details().length; i++) {
                for (Product p1 : CacheData.allProductList) {
                    if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                        System.out.printf("\t- 상세재료: %8s\t|| 재료개수: %5d\n", p1.getP_name(), p1.getP_count());
                    }
                }
            }
        }
        System.out.println("\t=============================================================================================================");
    }

    @Override
    public void ad_add() throws IOException{
        List<MasterRc> masterProductList = CacheData.masterRcList;

        try {
            System.out.println();
            System.out.println("\n\t[ 2. 신규 사장 추천 조합 등록 ]");
            System.out.println("\t=============================================================================================================");
            System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                    "구분번호", "이름", "칼로리", "금액", "재료(" + materialDetailCount + ")");
            System.out.println("\t=============================================================================================================");

            List<Product> selectedProducts = new ArrayList<>();
            int[] detailNum = new int[materialDetailCount];

            int idx = 1;
            System.out.printf("\n\t*** [안내] 조합에 들어갈 재료의 갯수: %d ***\n", materialDetailCount);
            while (idx <= materialDetailCount) {
                System.out.print("\t▶ 조합에 들어갈 재료(" + idx + ")의 구분번호 입력: ");
                int pointNumber = Integer.parseInt(br.readLine());
                boolean found = false;

                for (Product p : CacheData.allProductList) {
                    if (pointNumber == p.getP_checkNumber() && p.getSaleflag()) {
                        idx++;
                        found = true;
                        selectedProducts.add(p);
                        detailNum [idx-2] = pointNumber;

                        System.out.println("\t▷ 선택한 재료 : ");
                        for (int i : detailNum) {
                            for (Product p1 : CacheData.allProductList) {
                                if (i == p1.getP_checkNumber()){
                                    System.out.println("\t\t-이름 : " + p1.getP_name() + ", 갯수 : " + p1.getP_count());
                                }
                            }
                        }
                    }
                }

                if (!found) {
                    System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
                }
            }

            // 칼로리 총합 계산
            int calTotalcalorie = calculateTotalCalorie_01(detailNum);
            // 금액 총합 계산
            int originTotalPrice = calculateTotalPrice_01(detailNum);
            // 재고 개수 계산
            int calCount = calculateMinCount_01(detailNum);

            System.out.println("\n\t*** [안내] '사장추천'은 위의 '조합재료합계' 보다 적은 금액을 입력해주세요. ***");
            System.out.println("\t▷ 사장추천 조합재료 합계 : " + originTotalPrice);
            System.out.print("\t▶ 조합 정보에 들어갈 [구분번호,이름,금액] 입력(스페이스로 구분) : ");
            String input = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(input, " ");

            if (tokenizer.countTokens() != 3) {
                System.out.println("\t[!] 입력한 항목의 갯수가 맞지 않습니다.");
                return;
            }

            int r_checkNumber = Integer.parseInt(tokenizer.nextToken());
            String r_name = tokenizer.nextToken();
            int r_price = Integer.parseInt(tokenizer.nextToken());

            // 할인율 계산
            double calculateDiscount = 100 - (((double) r_price / originTotalPrice) * 100);

            // 이미 존재하는 구분번호인지 확인
            boolean isDuplicate = false;
            for (MasterRc existingRc : masterProductList) {
                if (r_checkNumber == existingRc.getR_checkNumber()) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                return;
            } else {
                System.out.print("\t▶ 저장하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    // MasterRc 객체 생성 및 리스트에 추가
                    MasterRc masterRc = new MasterRc(r_checkNumber, ProductType.RCMND, r_name, calTotalcalorie, r_price, selectedProducts, calculateDiscount, calCount, Saleflag);
                    masterProductList.add(masterRc);
                    System.out.println("\t「 저장되었습니다. 」");
                } else {
                    System.out.println("\t[!] 올바른 값을 입력하시오.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
        }

    }

    @Override
    public void ad_modify() throws IOException {
        List<MasterRc> masterProductList = CacheData.masterRcList;
        int modifyNum = 9;

        // 직렬화 주석 231018
//            FileMg f = new FileMg();

        System.out.println("\n\t[ 3. 조합정보 변경 ]");
        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
        int pointNumber = Integer.parseInt(br.readLine());
        boolean found = false;

        for (MasterRc masterRc : masterProductList) {
            if (pointNumber == masterRc.getR_checkNumber()) {
                found = true;

                System.out.println("\t=============================================================================================================");
                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "이름", "칼로리", "금액", "개수", "판매여부", "재료(" + materialDetailCount + ")");
                
                // 판매정보 출력
                String saleText = "";
                saleText = masterRc.getSaleflag() ? SaleTrue : SaleFalse;

                // 수량 현행화
                int calTotalMin = calculateMinCount_01(masterRc.r_details);
                masterRc.setR_count(calTotalMin);

                System.out.printf("\t|| %5d || %8s || %5d || %5d || %5d || %5s ",
                        masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                        , masterRc.getR_price(), masterRc.getR_count(), saleText);

                // 상세재료 출력
                String materialDetail = "";
                for (int i = 0; i < masterRc.getR_details().length; i++) {
                    for (Product p1 : CacheData.allProductList) {
                        if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                            if (i != 0){
                                materialDetail += ", ";
                            }
                            String materialItem = p1.getP_name();
                            materialDetail += materialItem;
                        }
                    }
                }
                System.out.printf("|| %-12s\n", materialDetail);
                System.out.println("\t=============================================================================================================");

                System.out.println("\n\t[ 변경할 내용 선택 ]-----------------------------------------------------------------------");
                System.out.println("\t1. 구분번호 2. 이름 3. 금액 4. 판매여부");
                System.out.println("\t----------------------------------------------------------------------------------------");
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
//                            f.list2FileOut();
                        System.out.println("\t「 수정되었습니다. 」");
                        break;
                    } else if (choice < 1 || choice > modifyNum) {
                        System.out.println("\t[!] 잘못된 선택입니다. 다시 선택하세요.");
                    } else {
                        if (choice == 4) {
                            System.out.println("\t*** [판매여부 안내] 1: 판매 O, 2: 판매 X");
                        }
                        System.out.print("\t▶ 새로운 값 입력 : ");
                        String newValue = br.readLine();
                        try {
                            switch (choice) {
                                case 1:
                                    int newCheckNumber = Integer.parseInt(newValue);
                                    masterRc.setR_checkNumber(newCheckNumber);
                                    break;
                                case 2:
                                    masterRc.setR_name(newValue);
                                    break;
                                case 5:
                                    int newCount = Integer.parseInt(newValue);
                                    masterRc.setR_count(newCount);
                                    break;
                                case 4:
                                    int newSale = Integer.parseInt(newValue);
                                    boolean p_saleflag = false;
                                    p_saleflag = newSale == 1 ? true : false;
                                    masterRc.setSaleflag(p_saleflag);
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
    }


    @Override
    public void ad_delete() throws IOException{
        try {
            // 역직렬화 기존 데이터 불러오기
            List<MasterRc> masterProductList = CacheData.masterRcList;
            // 직렬화 주석 231018
            //FileMg f = new FileMg();

            System.out.println("\n\t[ 4. 조합정보 삭제 ]");
            System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
            int pointNumber = Integer.parseInt(br.readLine());
            boolean found = false;

            int deleteIndex = -1; // 삭제할 아이템의 인덱스 초기화

            for (MasterRc masterRc : masterProductList) {
                if (pointNumber == masterRc.getR_checkNumber()) {
                    found = true;

                    System.out.println("\t=============================================================================================================");
                    System.out.printf("\t|| %5s || %-9s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "구분번호", "이름", "칼로리", "금액", "개수", "판매여부", "재료(" + materialDetailCount + ")");
                    System.out.println("\t=============================================================================================================");

                    // 판매정보 출력
                    String saleText = "";
                    saleText = masterRc.getSaleflag() ? SaleTrue : SaleFalse;

                    // 수량 현행화
                    int calTotalMin = calculateMinCount_01(masterRc.r_details);
                    masterRc.setR_count(calTotalMin);

                    System.out.printf("\t|| %5d || %8s || %5d || %5d || %5d || %5s ",
                            masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                            , masterRc.getR_price(), masterRc.getR_count(), saleText);

                    // 상세재료 출력
                    String materialDetail = "";
                    for (int i = 0; i < masterRc.getR_details().length; i++) {
                        for (Product p1 : CacheData.allProductList) {
                            if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                                if (i != 0){
                                    materialDetail += ", ";
                                }
                                String materialItem = p1.getP_name();
                                materialDetail += materialItem;
                            }
                        }
                    }
                    System.out.printf("|| %-12s\n", materialDetail);
                    System.out.println("\t=============================================================================================================");

                    System.out.print("\t삭제하시겠습니까?(Y/N) : ");
                    char x = br.readLine().charAt(0);
                    if (x == 'Y' || x == 'y') {
                        deleteIndex = pointNumber-1; // 삭제 대상 재료의 인덱스 설정
                        masterProductList.remove(deleteIndex);
                        System.out.println("\t「 삭제되었습니다. 」");
                        break;
                    } else {
                        System.out.println("\t[!] 올바른 값을 입력하시오.");
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("\t구분번호가 일치하지 않습니다");
                return; // 일치하지 않으면 삭제 작업을 하지 않고 종료
            }

        } catch (NumberFormatException e) {
            System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
        }
    }

    public void exit()
    {
        System.out.println("\n\t「 관리자 메뉴로 돌아갑니다. 」");
        KioskMg.masterrcflag = false;
    }
}
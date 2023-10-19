import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;

class MasterRc implements Serializable ,Impl_admin
{
    private static final long serialVersionUID = -2026629343147755130L;
    Product product = new Product();
    String appDir = System.getProperty("user.dir");
    private transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int r_checkNumber;
    private String r_name;
    private int r_totalcalorie;
    private int r_price;
    private double r_discount;
    private List<Product> r_products; // 재료 목록
    private ProductType type;
    private int r_count;
    private int r_limitCount;

    public ProductType getType() { return type; }

    public MasterRc(int r_checkNumber, ProductType type, String r_name, int r_totalcalorie, int r_price, List<Product> r_products,double r_discount, int r_count)
    {
        this.r_checkNumber = r_checkNumber;
        this.type = type;
        this.r_name = r_name;
        this. r_totalcalorie = r_totalcalorie;
        this.r_price = r_price;
        this.r_products = r_products;
        this.r_discount = r_discount;
        this.r_count = r_count;
    }

    public MasterRc(int r_checkNumber, String r_name, int r_totalcalorie, int r_price, List<Product> r_products)
    {
        this.r_checkNumber = r_checkNumber;
        this.r_name = r_name;
        this.r_price = r_price;
        this.r_products = r_products;
    }

    public MasterRc(){this(0, ProductType.RCMND, "", 0, 0, new ArrayList<>(),0,0);}
    public int getR_checkNumber(){return r_checkNumber;}
    public void setR_checkNumber(int r_checkNumber){this.r_checkNumber = r_checkNumber;}
    public String getR_name(){return r_name;}
    public void setR_name(String r_name){this.r_name = r_name;}
    public int getR_totalcalorie(){return r_totalcalorie;}
    public void setR_totalcalorie(int r_totalcalorie){this.r_totalcalorie = r_totalcalorie;}
    public int getR_price(){return r_price;}
    public void setR_price(int r_price){this.r_price = r_price;}
    public List<Product> getR_products(){return r_products;}
    public double getR_discount(){return r_discount;}
    public void setR_discount(double r_discount){this.r_discount = r_discount;}
    public int getR_count() { return r_count; }
    public void setR_count(int r_count) { this.r_count = r_count; }
    public void setR_products(List<Product> r_products)
    {
        this.r_products = r_products;
    }
    public int getR_limitCount() { return r_limitCount; }
    public void setR_limitCount(int r_limitCount) { this.r_limitCount = r_limitCount; }

    // MasterRc 객체 내의 재료의 총 칼로리 계산
    private int calculateTotalCalorie(List<Product> products)
    {
        int totalCalorie = 0;
        for (Product product : products)
        {
            totalCalorie += product.getP_calorie();
        }
        r_totalcalorie = totalCalorie;
        return totalCalorie;
    }

    // MasterRc 객체 내의 재료의 총 금액 계산
    private int calculateTotalPrice(List<Product> products)
    {
        int totalPrice = 0;
        for (Product product : products)
        {
            totalPrice += product.getP_price();
        }
        return totalPrice;
    }

    // MasterRc 객체 내의 재고 개수 계산
    public int calculateMinCount(List<Product> products)
    {
        int minCount=10000;
        for (Product product : products)
        {
            if(minCount > product.getP_count())
                minCount = product.getP_count();
        }
        return minCount;
    }


    @Override
    public void ad_print()
    {
        List<MasterRc> masterProduct = CacheData.masterProductList;

        System.out.println("\n\t[ 1. 사장 추천 조합 출력 ]");
        System.out.println("\t===========================================================================================");
        System.out.printf("\t|| %5s || %8s || %5s || %5s || %5s || %5s \n",
                "구분번호", "이름", "칼로리", "금액", "개수", "재료");
        System.out.println("\t===========================================================================================");

        // existingList2에 있는 MasterRc 객체를 출력
        for (MasterRc masterRc : masterProduct)
        {
            
            System.out.printf("\t|| %5d || %8s || %5d || %5d || %5d ",
                    masterRc.getR_checkNumber(), masterRc.getR_name(),
                    r_totalcalorie, masterRc.getR_price(), masterRc.getR_count());
            String materialDetail="";
            for (int i = 0; i<masterRc.getR_products().size(); i++)
            {
                if(i!=0) materialDetail +=", ";
                String materialItem = masterRc.getR_products().get(i).getP_name();
                materialDetail += materialItem;
            }
            System.out.printf("|| %12s\n",materialDetail);


            // 재료정보 출력
            for (Product product : masterRc.getR_products())
            {
                System.out.printf("\t- 상세재료: %8s\t|| 재료개수: %5d\n",product.getP_name(),product.getP_count());
            }
            System.out.println();
        }
        System.out.println("\t===========================================================================================");

        KioskMg.masterrcflag = false;

    }


    @Override
    public void ad_add() throws IOException, ClassNotFoundException {
        List<MasterRc> list2 = CacheData.masterProductList;

        try {
            System.out.println();
            System.out.println("\n\t[ 2.신규 사장 추천 조합 등록 ]");
            System.out.println("\t===========================================================================================");
            System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                    "구분번호", "이름", "칼로리", "금액", "재료");
            System.out.println("\t===========================================================================================");

            List<Product> selectedProducts = new ArrayList<>();
            List<Product> product = CacheData.allProductList;

            int materialDetailCount = 3;
            while (true) {
                System.out.printf("\t*** [안내] 조합에 들어갈 재료의 갯수: %d\n", materialDetailCount);
                System.out.print("\t▶ 조합에 들어갈 재료의 구분번호 입력: ");
                int pointNumber = Integer.parseInt(br.readLine());
                if (pointNumber == 0) {
                    break;
                }

                boolean found = false;

                for (Product p : product) {
                    if (pointNumber == p.getP_checkNumber()) {
                        found = true;
                        selectedProducts.add(p);

                        System.out.println("\t▷ 선택한 재료:");
                        for (Product selected : selectedProducts) {
                            System.out.println("\t\t-이름: " + selected.getP_name() + ", 갯수: " + selected.getP_count());
                        }
                    }
                }

                if (!found) {
                    System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
                }
            }

            // 칼로리 총합 계산
            r_totalcalorie = calculateTotalCalorie(selectedProducts);
            // 금액 총합 계산
            r_price = calculateTotalPrice(selectedProducts);
            // 재고 개수 계산
            r_count = calculateMinCount(selectedProducts);

            System.out.println("\t▷ 사장추천 조합재료 합계 :" + r_price);
            System.out.println("\t*** [안내] '사장추천'은 위의 '조합재료합계' 보다 적은 금액을 입력해주세요.");

            System.out.print("\t▶ 조합 정보에 들어갈 [구분번호,이름,금액] 입력(스페이스로 구분) : ");
            String input = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(input, " ");

            if (tokenizer.countTokens() != 3) {
                System.out.println("\t입력한 항목의 갯수가 맞지 않습니다.");
                return;
            }

            int r_checkNumber = Integer.parseInt(tokenizer.nextToken());
            String r_name = tokenizer.nextToken();
            int r_price = Integer.parseInt(tokenizer.nextToken());

            // 할인율 계산
            double r_discount = 100 - ((r_price / r_price) * 100);

            // 이미 존재하는 구분번호인지 확인
            boolean isDuplicate = false;
            for (MasterRc existingRc : list2) {
                if (r_checkNumber == existingRc.getR_checkNumber()) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                return;
            } else {
                System.out.print("\t저장하시겠습니까?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    // MasterRc 객체 생성 및 리스트에 추가
                    MasterRc masterRc = new MasterRc(r_checkNumber, ProductType.RCMND, r_name, r_totalcalorie, r_price, selectedProducts, r_discount, r_count);
                    list2.add(masterRc);
                    System.out.println("\t「 저장되었습니다. 」");
                } else {
                    System.out.println("\t[!] 올바른 값을 입력하시오.");
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
        }

        KioskMg.masterrcflag = false;
    }


    @Override
    public void ad_modify() throws IOException, ClassNotFoundException {
        try {
            List<MasterRc> existingList2 = CacheData.masterProductList;
            List<MasterRc> list4 = CacheData.list4;

            // 직렬화 주석 231018
//            FileMg f = new FileMg();

            System.out.println();
            System.out.println("\n\t[ 3. 조합정보 변경 ]");
            System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
            int pointNumber = Integer.parseInt(br.readLine());
            boolean found = false;

            for (int i = 0; i < existingList2.size(); i++) {
                if (pointNumber == existingList2.get(i).getR_checkNumber()) {
                    found = true;
                    MasterRc masterRc = existingList2.get(i);

                    System.out.println("\t===========================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                            "구분번호", "이름", "칼로리", "금액", "재료");
                    System.out.printf("\t|| %5d || %5s || %5d || %5d ||",
                            masterRc.getR_checkNumber(), masterRc.getR_name(),
                            calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price());

                    // 재료정보 출력
                    for (Product product : masterRc.getR_products()) {
                        System.out.print(" "+product.getP_name());
                    }
                    System.out.println(" ||");
                    System.out.println("\t===========================================================================================");

                    System.out.println("\t변경할 내용을 선택하시오.");
                    System.out.println("\t1.구분번호 2. 이름 3. 금액");
                    System.out.println();
                    while (true) {
                        System.out.print("\t▶ 변경할 내용의 숫자 입력 (0: 변경 완료) : ");
                        int choice = Integer.parseInt(br.readLine());
                        if (choice == 0) {
//                            f.list2FileOut();
//                            f.list4FileOut();
                            System.out.println("\t「 수정되었습니다. 」");
                            break;
                        }
                        switch (choice) {
                            case 1:
                                System.out.print("\t새로운 구분번호 입력: ");
                                int newCheckNumber = Integer.parseInt(br.readLine());
                                masterRc.setR_checkNumber(newCheckNumber);
                                for (MasterRc p : list4) {
                                    if (p.getR_checkNumber() == pointNumber) {
                                        p.setR_checkNumber(newCheckNumber);
                                    }
                                }
                                break;
                            case 2:
                                System.out.print("\t새로운 이름 입력: ");
                                String newName = br.readLine();
                                masterRc.setR_name(newName);
                                for (MasterRc p : list4) {
                                    if (p.getR_checkNumber() == pointNumber) {
                                        p.setR_name(newName);
                                    }
                                }
                                break;
                            case 3:
                                System.out.print("\t새로운 금액 입력: ");
                                int newPrice = Integer.parseInt(br.readLine());
                                masterRc.setR_price(newPrice);
                                for (MasterRc p : list4) {
                                    if (p.getR_checkNumber() == pointNumber) {
                                        p.setR_price(newPrice);
                                    }
                                }
                                break;
                            default:
                                System.out.println("\t입력된 항목이 없습니다.");
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
        }
        KioskMg.masterrcflag = false;
    }


    @Override
    public void ad_delete() throws IOException, ClassNotFoundException {
        try {
            // 역직렬화 기존 데이터 불러오기
            List<MasterRc> existingList2 = CacheData.masterProductList;
            List<MasterRc> list4 = CacheData.list4;
            // 직렬화 주석 231018
            //FileMg f = new FileMg();

            System.out.println("\n\t[ 4. 조합정보 삭제 ]");
            System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
            int pointNumber = Integer.parseInt(br.readLine());
            boolean found = false;

            int deleteIndex = -1; // 삭제할 아이템의 인덱스 초기화

            for (int i = 0; i < existingList2.size(); i++) {
                if (pointNumber == existingList2.get(i).getR_checkNumber()) {
                    found = true;
                    MasterRc masterRc = existingList2.get(i);

                    System.out.println("\t===========================================================================================");
                    System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                            "구분번호", "이름", "칼로리", "금액", "재료");
                    System.out.printf("\t|| %5d || %5s || %5d || %5d ||",
                            masterRc.getR_checkNumber(), masterRc.getR_name(),
                            calculateTotalCalorie(masterRc.getR_products()), masterRc.getR_price());

                    // 재료정보 출력
                    for (Product product : masterRc.getR_products()) {
                        System.out.print(" "+product.getP_name());
                    }
                    System.out.println(" ||");
                    System.out.println("\t===========================================================================================");

                    System.out.print("\t삭제하시겠습니까?(Y/N) : ");
                    char x = br.readLine().charAt(0);
                    if (x == 'Y' || x == 'y') {
                        deleteIndex = i; // 삭제 대상 재료의 인덱스 설정
                        for (Iterator<MasterRc> iterator = list4.iterator(); iterator.hasNext(); ) {
                            MasterRc p = iterator.next();
                            if (p.getR_checkNumber() == pointNumber) {
                                iterator.remove(); // list4에서 안전하게 제거
                            }
                        }
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

            // 삭제 대상 재료가 설정된 경우에만 삭제 수행
            if (deleteIndex != -1) {
                existingList2.remove(deleteIndex);
                System.out.println("\t「 삭제되었습니다. 」");

//                f.list2FileOut();
//                f.list4FileOut();
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
        }

        KioskMg.masterrcflag = false;
    }

}

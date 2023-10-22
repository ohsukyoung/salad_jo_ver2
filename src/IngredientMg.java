import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class IngredientMg implements Impl_admin{

    public final int E_PRINT = 1;                    //-- 요소(회원) 추가
    public final int E_ADD = 2;
    public final int E_MOD = 3;
    public final int E_DEL = 4;
    public final int E_EXIT = 5;


    private static BufferedReader br;		         //-- 사용자가 입력시 활용
    private static Integer sel;				         //-- 선택 값


    Product product = new Product();

    static
    {
        //BufferedReader 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자 입력값 초기화
        sel = 1;

    }
    public void menuDisp()
    {
        System.out.println("\n\t[ 재료관리 메뉴 선택 ]===========");
        System.out.println("\t1. 재료 목록 출력");
        System.out.println("\t2. 신규재료 등록");
        System.out.println("\t3. 재료정보 변경");
        System.out.println("\t4. 재료정보 삭제");
        System.out.println("\t5. 관리자 메뉴 화면으로 이동");
        System.out.println("\t=================================");
        System.out.print("\t▶ 메뉴 선택(1~5) : ");
    }

    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }
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
                    System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                    System.out.println("\t==========================================================================================================");

                    // Iterator 활용하여 출력
                    Iterator<Product> itList = product.iterator();
                    while (itList.hasNext())
                    {
                        Product itS = itList.next();
                        String saleText = "";
                        saleText = itS.getSaleflag()? "판매 O": "판매 X";
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                case 2 :
                    System.out.println("\n\t[ 2. 선택 재료정보 출력 ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
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
                            System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
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
        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
        System.out.println("\t==========================================================================================================");
        System.out.println("\t*** [분류번호 안내] 1: 사장추천, 3:음료, 4:사이드, 5:베이스, 6:메인토핑, 7:사이드토핑, 8:소스");
        System.out.println("\t*** [판매여부 안내] 1: 판매 O, 2: 판매 X");

        System.out.print("\t▶ 재료정보 입력 (스페이스로 구분): ");
        String input = br.readLine();

        // 토크나이저로 스페이스로 끊어주기
        int addTokenizer = 9;
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        if (tokenizer.countTokens() != addTokenizer) {
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
            int p_saleflagInt = Integer.parseInt(tokenizer.nextToken());
            boolean p_saleflag = false;
            p_saleflag = p_saleflagInt == 1? true : false;

            List<Product> allProductList;
            allProductList = CacheData.allProductList;

            boolean m = false;
            for (int i = 0; i < allProductList.size(); i++){
                if (p_checkNumber == allProductList.get(i).getP_checkNumber() && allProductList.get(i).getSaleflag()){
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
                    Product product = new Product(p_checkNumber, productType, p_material, p_name, p_unit, p_count, p_calorie, p_stock, p_price, p_saleflag);
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
    }



    @Override
    public void ad_modify() throws IOException, ClassNotFoundException {
        List<Product> allProductList = CacheData.allProductList;
//        List<Product> list3 = CacheData.list3;
        int modifyNum = 9;

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

        for (int i = 0; i < allProductList.size(); i++) {
            if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                found = true;
                Product itS = allProductList.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "판매 O": "판매 X";
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
                System.out.println("\t==========================================================================================================");

                System.out.println("\n\t[ 변경할 내용 선택 ]-----------------------------------------------------------------------");
                System.out.println("\t1.구분번호 2. 분류번호 3. 이름 4. 단위 5. 개수 6. 칼로리 7. 적정재고 8. 금액 9. 판매여부");
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
                        // 변경 완료 시, 변경된 정보를 저장
                        // 직렬화 주석 231018
//                        FileMg f = new FileMg();
//                        f.list1FileOut();
//                        f.list3FileOut();
                        System.out.println("\t「 수정되었습니다. 」");
                        return; // 메소드를 빠져나갑니다.
                    } else if (choice < 1 || choice > modifyNum) {
                        System.out.println("\t[!] 잘못된 선택입니다. 다시 선택하세요.");
                    } else {
                        if(choice == 9){
                            System.out.println("\t*** [판매여부 안내] 1: 판매 O, 2: 판매 X");
                        }
                        System.out.print("\t▶ 새로운 값 입력 : ");
                        String newValue = br.readLine();
                        try {
                            switch (choice) {
                                case 1:
                                    int newCheckNumber = Integer.parseInt(newValue);
                                    itS.setP_checkNumber(newCheckNumber);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_checkNumber(newCheckNumber);
//                                        }
//                                    }
                                    break;
                                case 2:
                                    int newMaterial = Integer.parseInt(newValue);
                                    itS.setP_material(newMaterial);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_material(newMaterial);
//                                        }
//                                    }
                                    break;
                                case 3:
                                    itS.setP_name(newValue);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_name(newValue);
//                                        }
//                                    }
                                    break;
                                case 4:
                                    itS.setP_unit(newValue);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_unit(newValue);
//                                        }
//                                    }
                                    break;
                                case 5:
                                    int newCount = Integer.parseInt(newValue);
                                    itS.setP_count(newCount);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_count(newCount);
//                                        }
//                                    }
                                    break;
                                case 6:
                                    int newCalorie = Integer.parseInt(newValue);
                                    itS.setP_calorie(newCalorie);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_calorie(newCalorie);
//                                        }
//                                    }
                                    break;
                                case 7:
                                    int newStock = Integer.parseInt(newValue);
                                    itS.setP_stock(newStock);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_stock(newStock);
//                                        }
//                                    }
                                    break;
                                case 8:
                                    int newPrice = Integer.parseInt(newValue);
                                    itS.setP_price(newPrice);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_price(newPrice);
//                                        }
//                                    }
                                    break;
                                case 9:
                                    int newSale = Integer.parseInt(newValue);
                                    boolean p_saleflag = false;
                                    p_saleflag = newSale == 1? true : false;
                                    itS.setSaleflag(p_saleflag);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setSaleflag(p_saleflag);
//                                        }
//                                    }
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
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
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

    }
    public void exit()
    {
        System.out.println("\n\t「 관리자 메뉴로 돌아갑니다. 」");
        KioskMg.ingredientflag = false;
    }
}


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 키오스크 ----------------------------------------------------------------
*/
class Kiosk {
    final String USER_NAME = "고객";      //-- 사용자 임의의 이름
    public Imp_info info;                      //-- info 틀 생성

    static boolean storeflag = true;

    public Kiosk(ProductService productService) {
        this.info = new InfoService(productService);
    }
    public void kioskStart() throws IOException {
        storePack();    // 포장 or 매장 여부
        menuDisp();     // 사용자 메뉴 선택

        // 선택값 체크
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 4;
        int userSelect = selectMenu.menuSelect(listSize,1);
        menuRun(userSelect);

        // 선택값 리스트에 담기
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderTotal();
        orderSetting.printOrderList();

        cart ca = new cart();               //-- 장바구니 인스턴스 생성
        do
        {
            ca.menuDis();
            ca.menuSel(userSelect);
            ca.menuR(userSelect);
        }
        while (true);
    }

    public void storePack() {
        List<Order> OrderList = CacheData.orderOuterList;
        String payMe = null;
        System.out.println("\n\t[ 포장/매장 선택 ]=============");
        System.out.println("\t1. 포장");
        System.out.println("\t2. 매장");
        System.out.println("\t==============================");
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 2;
        int adminNum = -1;
        int userSelect = selectMenu.menuSelect(listSize,adminNum);

        if(userSelect==1)
        {
            storeflag = false;
        }
        else {
            storeflag = true;
        }

        if(userSelect == adminNum){
            System.out.println("\n\t「 관리자화면으로 이동합니다. 」");
            ad_login al = new ad_login();

            try {
                al.adLogin();
            } catch (IOException e) {
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            }
        }

        // 선택값 배열-유저 임시 이름 넣기
        List<Order> outerList = CacheData.orderOuterList;           //-- 사용자 선택 바깥(틀) 생성
        List<Order> orderOuterList = CacheData.orderOuterList;      //-- 사용자 선택 안쪽(값) 생성

        if (orderOuterList.isEmpty()) {
            orderOuterList.add(new Order(USER_NAME + 1, "20231012100200", 0, 0));
        }
        else {
            int outerListSize = outerList.size();
            outerList.set(outerList.size()-1,new Order(USER_NAME + outerListSize, "20231012100200", 0, 0));
        }
    }

    public void menuDisp() {
        System.out.println("\n\t[ 사용자 메뉴 선택 ]===========");
        System.out.println("\t1. 사장추천");
        System.out.println("\t2. 나만의 샐러드");
        System.out.println("\t3. 음료");
        System.out.println("\t4. 사이드");
//        System.out.println("\t - 뒤로가기(c)");
        System.out.println("\t=============================");
    }

    public void menuRun(int userSelect) {
        // userSelect를 ProductType으로 변환
        ProductTypeChange productTypeChange = new ProductTypeChange();
        ProductType productType = productTypeChange.ProductTypeChange(userSelect);

        switch (productType) { // DESC: swtich문의 조건에 String 타입을 넣게되면, case문에서 enum타입으로 비교할 수 가 없음
            case RCMND:
                menuRcmd();
                break;
            case MY_SALAD:
                menuMySalad();
                break;
            case DRINK:
                menuDrink();
                break;
            case SIDE:
                menuSide();
                break;
            case CANCEL:
                menuCancel();
                break;
        }
    }

    public void menuRcmd() {
        System.out.println("\n\t[ 1. 사장추천 ]");
        info.printInfo(ProductType.RCMND);
    }

    public void menuMySalad() {
        System.out.println("\n\t[ 2. 나만의 샐러드 ]");

        System.out.println("\t[ 베이스 ■ ■ ■ ■ ]");
        info.printInfo(ProductType.S_BASE);

        System.out.println("\t[ ■ 메인토핑 ■ ■ ■ ]");
        info.printInfo(ProductType.S_MAIN);

        System.out.println("\t[ ■ ■ 사이드토핑 ■ ■ ]");
        info.printInfo(ProductType.S_SIDE);

        System.out.println("\t[ ■ ■ ■ 소스 ■ ]");
        info.printInfo(ProductType.S_SOURCE);
    }

    public void menuDrink() {
        System.out.println("\n\t[ 3. 음료 ]");
        info.printInfo(ProductType.DRINK);
    }

    public void menuSide() {
        System.out.println("\n\t[ 4. 사이드 ]");
        info.printInfo(ProductType.SIDE);
    }

    public void menuCancel() {

    }
}
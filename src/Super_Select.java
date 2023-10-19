/*
 메뉴 선택 ----------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

interface Super_Select_Interface {
    int menuSelect(int listSize,int minNum);
}

// 선택
public abstract class Super_Select implements Super_Select_Interface {
    static BufferedReader br;
    protected String message;   // 입력안내 메세지
    protected String errorMsg;  // 에러 메세지
    int minNum;                 // 선택 최소값

    public int menuSelect(int listSize,int minNum) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                String userInput = br.readLine();
                if (userInput.isEmpty()) {
                    System.out.println("\t[!] 잘못 입력되었습니다. 다시 입력해주세요.");
                    continue;
                }
                userSelect = Integer.parseInt(userInput);
                if (userSelect < minNum || userSelect > listSize)
                    System.out.println(errorMsg);
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }

    public int menuSelectProduct(int listSize,int minNum,List<Product> productList,int menuUserSelect) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                String userInput = br.readLine();
                if (userInput.isEmpty()) {
                    System.out.println("\t[!] 잘못 입력되었습니다. 다시 입력해주세요.");
                    continue;
                }
                userSelect = Integer.parseInt(userInput);
                if (userSelect < minNum || userSelect > listSize){
                    System.out.println(errorMsg);
                }
                if(userSelect > listSize){
                    String userSelectName = productList.get(menuUserSelect-1).getP_name();
                    System.out.println("\t▷ [" + userSelectName + "] 구매가능 개수 : " + listSize);
                }
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }

    public int menuSelectMasterRc(int listSize,int minNum,List<MasterRc> productList,int menuUserSelect) {
        int userSelect = 0;
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.printf(message);
                String userInput = br.readLine();
                if (userInput.isEmpty()) {
                    System.out.println("\t[!] 잘못 입력되었습니다. 다시 입력해주세요.");
                    continue;
                }
                userSelect = Integer.parseInt(userInput);
                if (userSelect < minNum || userSelect > listSize){
                    System.out.println(errorMsg);
                }
                if(userSelect > listSize){
                    String userSelectName = productList.get(menuUserSelect-1).getR_name();
                    System.out.println("\t▷ [" + userSelectName + "] 구매가능 개수 : " + listSize);
                }
            } while (userSelect < minNum || userSelect > listSize);
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
        return userSelect;
    }
}

// 메뉴추가안내 후 메뉴, 갯수 입력받기
class SelectContinue extends Super_Select {
    public void menuSelectProduct(List<Product> productList){
        this.message    = "\n\t▶ 메뉴를 추가 하시겠습니까?(Y/N): ";
        this.errorMsg   = "\t[!] 잘못 입력되었습니다. 다시 입력해주세요.";

        br = new BufferedReader(new InputStreamReader(System.in));
        InsertSelectValue insertSelectValue = new InsertSelectValue();
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        OrderValues orderValues = null;
        char answer;

        try {
            while (true){

                do {
                    // 장바구니 OuterList에 있는 이름과 같은 갯수를 빼서 limit에 담아두고, limit을 select에 넘겨주기
                    for (Product product: productList){
                        product.setP_limitCount(product.getP_count()-product.getP_stock());
                        for (OrderValues orderValues2: orderInnerValues){
                            if(orderValues2.getName().equals(product.getP_name())){
                                product.setP_limitCount(product.getP_limitCount() - orderValues2.getCount());
                            }
                        }
                    }

                    System.out.printf(message);
                    answer = br.readLine().charAt(0);
                }while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n');
                if(answer == 'N' || answer == 'n') // N이거나 n이면 -> 반복문 빠져나가기
                    break;

                orderValues = insertSelectValue.insertSelectValueProduct(productList);
                orderInnerValues.add(orderValues);

                System.out.println(orderInnerValues);            // test 선택된 메뉴 출력

            }
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
    }

    public void menuSelectMasterRc(List<MasterRc> productList){
        this.message    = "\n\t▶ 메뉴를 추가 하시겠습니까?(Y/N): ";
        this.errorMsg   = "\t[!] 잘못 입력되었습니다. 다시 입력해주세요.";

        br = new BufferedReader(new InputStreamReader(System.in));
        InsertSelectValue insertSelectValue = new InsertSelectValue();
        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        OrderValues orderValues = null;
        char answer;

        try {
            while (true){

                do {
                    // 장바구니 OuterList에 있는 이름과 같은 갯수를 빼서 limit에 담아두고, limit을 select에 넘겨주기
                    for (MasterRc masterRc: productList){
                        masterRc.setR_limitCount(masterRc.getR_count());
                        for (OrderValues orderValues2: orderInnerValues){
                            if(orderValues2.getName().equals(masterRc.getR_name())){
                                masterRc.setR_limitCount(masterRc.getR_limitCount() - orderValues2.getCount());
                            }
                        }
                    }

                    System.out.printf(message);
                    answer = br.readLine().charAt(0);
                }while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n');
                if(answer == 'N' || answer == 'n') // N이거나 n이면 -> 반복문 빠져나가기
                    break;

                orderValues = insertSelectValue.insertSelectValueMaster(productList);
                orderInnerValues.add(orderValues);

                System.out.println(orderInnerValues);            // test 선택된 메뉴 출력

            }
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        }
    }
}

// 선택 값 삽입
class InsertSelectValue{
    public OrderValues insertSelectValueProduct(List<Product> productList){     //Procudt 타입의 메뉴 선택 선택값 삽입
        // 유저 메뉴 숫자 선택
        SelectMenu selectMenu = new SelectMenu();
        SelectCount selectCount = new SelectCount();

        int userSelect = selectMenu.menuSelect(productList.size(),1);
        int pdCount = productList.get(userSelect - 1).getP_limitCount();

        int userStock = selectCount.menuSelectProduct(pdCount,0,productList,userSelect); // 유저 재고 개수 선택

        // 유저 선택값 만들기
        return new OrderValues(
                productList.get(userSelect - 1).getP_name(),    // 유저 선택 제품>이름
                userStock,                                      // 유저 선택 개수
                productList.get(userSelect - 1).getP_calorie(), // 유저 선택 제품>칼로리
                productList.get(userSelect - 1).getP_price()    // 유저 선택 제품>가격
        );
    }

    public OrderValues insertSelectValueMaster(List<MasterRc> productList){     //MasterRc 타입의 메뉴 선택 선택값 삽입
        // 유저 메뉴 숫자 선택
        SelectMenu selectMenu = new SelectMenu();
        SelectCount selectCount = new SelectCount();

        int userSelect = selectMenu.menuSelect(productList.size(),1);
        int totalPdCount = productList.get(userSelect - 1).getR_count(); // 선택한 재고 개수
        int pdCount = totalPdCount;

        List<OrderValues> orderInnerValues = CacheData.orderInnerValues;
        for (OrderValues orderValues: orderInnerValues){
            if(orderValues.getName().equals(productList.get(userSelect - 1).getR_name())){
                pdCount -= orderValues.getCount();
            }
        }

        int userStock = selectCount.menuSelectMasterRc(pdCount,0,productList,userSelect); // 유저 재고 개수 선택
        
        // 유저 선택값 만들기
        return new OrderValues(
                productList.get(userSelect - 1).getR_name(),    // 유저 선택 제품>이름
                userStock,                                      // 유저 선택 개수
                productList.get(userSelect - 1).getR_totalcalorie(),  // 유저 선택 제품>칼로리
                productList.get(userSelect - 1).getR_price()   // 유저 선택 제품>가격
        );
    }
}



// 개수 선택
class SelectCount extends Super_Select {
    public SelectCount() {
        this.message    = "\t▶ 수량 선택: ";
        this.errorMsg   = "\t[!] 남은 수량에서 벗어났습니다. 다시 입력해주세요.";
//        this.minNum = 0;
    }

    @Override
    public int menuSelect(int listSize,int minNum) {
        super.minNum = minNum;
        return super.menuSelect(listSize,minNum);
    }

    public int menuSelectProduct(int listSize,int minNum,List<Product> productList, int menuUserSelect) {
        super.minNum = minNum;
        return super.menuSelectProduct(listSize,minNum,productList,menuUserSelect);
    }

    public int menuSelectMasterRc(int listSize,int minNum,List<MasterRc> productList, int menuUserSelect) {
        super.minNum = minNum;
        return super.menuSelectMasterRc(listSize,minNum,productList,menuUserSelect);
    }

}
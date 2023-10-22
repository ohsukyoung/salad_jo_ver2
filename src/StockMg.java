import java.io.*;
import java.util.HashMap;
import java.util.List;

public class StockMg implements Serializable {
// 재고관리 메뉴 클래스

    public final int E_SETTING = 1;                    //-- 요소(회원) 추가
    public final int E_SOLDOUT = 2;
    public final int E_EXIT = 3;
    private static BufferedReader br;		         //-- 사용자가 입력시 활용
    private static Integer sel;				         //-- 선택 값

    List<Product> allProductList = CacheData.allProductList;

    static
    {
        //BufferedReader 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자 입력값 초기화
        sel = 1;
    }



    public void menuDisp()
    {
        System.out.println("\n\t[ 재고관리 메뉴 선택 ]===========");
        System.out.println("\t1. 재료셋팅");
        System.out.println("\t2. 품절관리");
        System.out.println("\t3. 관리자 메뉴 화면으로 이동");
        System.out.println("\t=================================");
        System.out.print("\t▶ 메뉴 선택(1~3) : ");
    }
    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }
    public void menuRun() throws IOException, ClassNotFoundException
    {
        switch (sel)
        {
            case E_SETTING : product_setting(); break;
            case E_SOLDOUT : soldout_management(); break;
            case E_EXIT : exit(); break;
            default : System.out.print("\t[!] 메뉴 선택 오류");
        }
    }


    public void product_setting() throws IOException, ClassNotFoundException {
        System.out.println("\n\t[ 1. 재료세팅 ]");
        while (true) {
            try {
                System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                int pointNumber = Integer.parseInt(br.readLine());
                boolean found = false;

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_material()) {
                        found = true;
                        break; // 해당 분류번호를 찾았으므로 루프 종료
                    }
                }

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                        found = true;
                        Product itS = allProductList.get(i);

                        System.out.println("\t==========================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                        System.out.println("\t==========================================================================================================");

                        String saleText = "";
                        saleText = itS.getSaleflag() ? "판매 O" : "판매 X";
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                        System.out.println();

                        System.out.print("\t▶ 변경할 항목의 개수 입력 : ");
                        int newCount = Integer.parseInt(br.readLine());
                        allProductList.get(i).setP_count(newCount);

                        break;
                    }
                }

                if (found) {
                    break; // 올바른 분류번호를 찾았으므로 루프 종료
                } else {
                    System.out.println("\t▶ 구분번호가 존재하지 않습니다.");
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
            }

        }
    }

    public void soldout_management() throws IOException, ClassNotFoundException {
        System.out.println("\n\t[ 2. 품절관리 ]");
        while (true) {
            try {
                System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                int pointNumber = Integer.parseInt(br.readLine());
                boolean found = false;

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_material()) {
                        found = true;
                        break; // 해당 분류번호를 찾았으므로 루프 종료
                    }
                }

                for (int i = 0; i < allProductList.size(); i++) {
                    if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                        found = true;
                        Product itS = allProductList.get(i);

                        System.out.println("\t==========================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                        System.out.println("\t==========================================================================================================");

                        String saleText = "";
                        saleText = itS.getSaleflag() ? "판매 O" : "판매 X";
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                        System.out.println();

                        System.out.print("\t▶ 변경할 항목의 적정재고 입력 : ");
                        int newStock = Integer.parseInt(br.readLine());
                        allProductList.get(i).setP_stock(newStock);
                    }
                }

                if (found) {
                    break; // 올바른 분류번호를 찾았으므로 루프 종료
                } else {
                    System.out.println("\t▶ 구분번호가 존재하지 않습니다.");
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
            }
        }
    } // soldout_management
    public void exit()
    {
        System.out.println("\n\t「 관리자 메뉴로 돌아갑니다. 」");
        KioskMg.stockflag = false;
    }
}
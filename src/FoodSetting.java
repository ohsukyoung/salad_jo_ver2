import java.io.*;
import java.util.Iterator;
import java.util.List;

public class FoodSetting implements Serializable {
// 재고관리 메뉴 클래스
    List<Product> allProductList = CacheData.allProductList;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
                    }
                }

                if (found) {
                    break; // 올바른 분류번호를 찾았으므로 루프 종료
                } else {
                    System.out.println("\t▶ 구분번호가 존재하지 않습니다.");
                }

                KioskMg.foodadminflag = false;
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

                KioskMg.foodadminflag = false;
                break;

            } catch (NumberFormatException e) {
                System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
            }
        }
    } // soldout_management
}
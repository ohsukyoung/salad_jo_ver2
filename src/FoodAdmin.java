import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.io.InputStreamReader;

public class FoodAdmin implements Serializable {
    // Assuming Product and MasterRc classes are defined somewhere

    List<Product> allProductList = CacheData.allProductList;
    List<MasterRc> masterProductList = CacheData.masterRcList;

    int materialDetailCount = 3;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String SaleTrue = "판매 O";
    String SaleFalse = " 판매 X";

    public void setting_print() throws IOException, ClassNotFoundException{
//        FileMg f = new FileMg();
//        f.list1FileIn();

        System.out.println("\n\t[ 판매정보 출력 ]===============");
        System.out.println("\t1. 판매 재료정보 출력\n\t2. 판매 사장추천 정보 출력");
        System.out.println("\t==============================");

        while (true) {
            try {
                System.out.print("\t▶ 선택할 항목의 숫자 입력 : ");
                int z = Integer.parseInt(br.readLine());
                switch (z)
                {
                    case 1:
                        System.out.println("\n\t[ 1. 판매 재료정보 출력 ]");
                        System.out.println("\t=============================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                        System.out.println("\t=============================================================================================================");

                        for(Product allProductList:allProductList){
                            if(allProductList.getSaleflag()){     // 판매정보가 true 일때
                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", allProductList.getP_checkNumber(),
                                        allProductList.getP_material(), allProductList.getP_name(), allProductList.getP_unit(), allProductList.getP_count(), allProductList.getP_calorie(),
                                        allProductList.getP_stock(), allProductList.getP_price(), SaleTrue);
                            }
                        }
                        System.out.println("\t=============================================================================================================");
                        System.out.println();
                        return;

                    case 2:
                        System.out.println("\n\t[ 2. 사장 추천 조합 출력 ]");
                        System.out.println("\t=============================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "구분번호", "이름", "칼로리", "금액", "재료", "개수", "판매여부", "재료(" + materialDetailCount +")");
                        System.out.println("\t=============================================================================================================");

                        // MasterRc 객체를 출력
                        for (MasterRc masterRc : masterProductList) {
                            if(masterRc.getSaleflag()){     // 판매정보가 true 일때
                                System.out.printf("\t|| %5d || %5s || %5d || %5d || %5d \n",
                                    masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie()
                                        , masterRc.getR_price(), masterRc.getR_count());

                                // 상세재료 출력
                                String materialDetail="";
                                for (int i = 0; i<masterRc.getR_products().size(); i++)
                                {
                                    if(i!=0) materialDetail +=", ";
                                    String materialItem = masterRc.getR_products().get(i).getP_name();
                                    materialDetail += materialItem;
                                }
                                System.out.printf("|| %-12s ||\n",materialDetail);

                                // 재료정보 출력
                                for (Product product : masterRc.getR_products()) {
                                    System.out.printf("\t- 상세재료: %8s\t|| 재료개수: %5d\n",product.getP_name(),product.getP_count());
                                }
                            }
                        }
                        System.out.println("\t=============================================================================================================");
                        System.out.println();

                        return;
                    default:
                        System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\t[!] 올바른 숫자를 입력하세요.");
            }
        }
    }

    public void product_setting() throws IOException, ClassNotFoundException {

        while (true) {
            try {
                System.out.println("\n\t[ 2. 판매항목 세팅 ]=============");
                System.out.println("\t1. 판매재료 세팅  \n\t2. 판매사장조합 세팅");
                System.out.println("\t============================");
                System.out.print("\t▶ 선택할 항목의 숫자 입력 : ");
                int c = Integer.parseInt(br.readLine());

                switch (c) {
                    case 1:
                        System.out.println("\n\t[ 1. 판매재료 세팅 ]");

                        // 판매X 출력
                        System.out.println("\n\t[ 판매X 목록 출력 ]");
                        System.out.println("\t==========================================================================================================");
                        System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                        System.out.println("\t==========================================================================================================");

                        Iterator<Product> itList = allProductList.iterator();
                        while (itList.hasNext())
                        {
                            Product itS = itList.next();
                            if(!itS.getSaleflag()){
                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                        itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                        itS.getP_stock(), itS.getP_price(), SaleFalse);
                            }
                        }
                        System.out.println("\t==========================================================================================================");
                        System.out.println();

                        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                        int pointNumber = Integer.parseInt(br.readLine());
                        boolean found = false;
                        boolean k = false;

                        for (int i = 0; i < allProductList.size(); i++) {
                            if (pointNumber == allProductList.get(i).getP_checkNumber() && allProductList.get(i).getSaleflag()) {
                                k = true;
                                break;
                            }
                        }
                        if (k) {
                            System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                            return;
                        }

                        for (int i = 0; i < allProductList.size(); i++) {
                            if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                                found = true;
                                Product selectedProduct = allProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액", "판매여부");
                                System.out.println("\t=============================================================================================================");

                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price(), SaleFalse);
                                System.out.println("\t=============================================================================================================");
                                System.out.println();

                                System.out.print("\t▶ 판매항목에 추가하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // 선택된 항목 저장
                                    selectedProduct.setSaleflag(true);
                                    System.out.println("\t「 저장되었습니다. 」");

                                    return;
                                } else
                                    return;
                            }
                        }
                        if (!found) {
                            System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
                            break;
                        }
                        break;

                    case 2:
                        System.out.println("\n\t[ 2. 판매사장조합 세팅 ]");
                        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                        int pointNumber2 = Integer.parseInt(br.readLine());
                        boolean found2 = false;
                        boolean p = false;
                        for (int i = 0; i < masterProductList.size(); i++) {
                            if (pointNumber2 == masterProductList.get(i).getR_checkNumber() && masterProductList.get(i).getSaleflag()) {
                                p = true;
                                break;
                            }
                        }
                        if (p) {
                            System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                            return;
                        }

                        for (int i = 0; i < masterProductList.size(); i++) {
                            if (pointNumber2 == masterProductList.get(i).getR_checkNumber()) {
                                found2 = true;
                                MasterRc selectedMasterRc = masterProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "이름", "칼로리", "금액", "재료");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||\n",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t=============================================================================================================");

                                System.out.println();
                                System.out.print("\t▶ 판매항목에 추가하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // 선택된 항목 저장
                                    selectedMasterRc.setSaleflag(true);
                                    System.out.println("\t「 저장되었습니다. 」");
                                    return;
                                } else
                                    return;
                            }
                        }

                        if (!found2) {
                            System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
                            break;
                        }
                        break;

                    default:
                        System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\t[!] 올바른 숫자를 입력하세요.");
            }
        }
    }

    public void soldout_management() throws IOException, ClassNotFoundException {
        while (true) {
            try {
                System.out.println("\n\t[ 3. 판매항목 제거 ]============");
                System.out.println("\t1. 판매재료 제거  \n\t2. 판매사장조합 제거");
                System.out.println("\t============================");
                System.out.print("\t▶ 선택할 항목의 숫자 입력 : ");
                int c = Integer.parseInt(br.readLine());

                switch (c) {
                    case 1:
                        System.out.println("\n\t[ 1.판매재료 제거 ]");
                        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                        int pointNumber = Integer.parseInt(br.readLine());
                        boolean found3 = false;

                        for (int i = 0; i < allProductList.size(); i++) {
                            if (pointNumber == allProductList.get(i).getP_checkNumber() && allProductList.get(i).getSaleflag()) {
                                found3 = true;
                                Product selectedProduct = allProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %9s  || %5s|| %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                                System.out.println("\t=============================================================================================================");

                                System.out.printf("\t|| %5s || %5s || %-9s || %5s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price());
                                System.out.println("\t=============================================================================================================");

                                System.out.print("\t▶ 판매항목에 제거하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // 선택된 항목 삭제
                                    selectedProduct.setSaleflag(false);
                                    System.out.println("\t「 저장되었습니다. 」");
                                    return;
                                } else
                                    return;
                            }
                        }

                        if (!found3) {
                            System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
                            break;
                        }
                        break;
                    case 2:
                        System.out.println("\n\t[ 2. 판매사장조합 제거 ]========================================================================");
                        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                        int pointNumber2 = Integer.parseInt(br.readLine());
                        boolean found4 = false;

                        for (int i = 0; i < masterProductList.size(); i++) {
                            if (pointNumber2 == masterProductList.get(i).getR_checkNumber() && masterProductList.get(i).getSaleflag()) {
                                found4 = true;
                                MasterRc selectedMasterRc = masterProductList.get(i);

                                System.out.println("\t=============================================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "이름", "칼로리", "금액", "재료");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||\n",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t=============================================================================================================");

                                System.out.print("\t▶ 판매항목에 제거하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // 선택된 항목 삭제
                                    selectedMasterRc.setSaleflag(false);
                                    System.out.println("\t「 저장되었습니다. 」");
                                    return;
                                } else
                                    return;
                            }
                        }

                        if (!found4) {
                            System.out.println("\t[!] 구분번호가 일치하지 않습니다.");
                            break;
                        }
                        break;
                    default:
                        System.out.println("\t[!] 잘못된 입력입니다. 다시 입력하세요.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\t[!] 올바른 숫자를 입력하세요.");
            }
        }
    }

}

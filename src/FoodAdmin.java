import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.io.InputStreamReader;

public class FoodAdmin implements Serializable {
    // Assuming Product and MasterRc classes are defined somewhere

    Product product = new Product();
    MasterRc masterrc = new MasterRc();

    KioskMg km = new KioskMg();

    List<Product> list1 = CacheData.allProductList;
    List<MasterRc> list2 = CacheData.masterProductList;

    List<Product> list3 = CacheData.list3;
    List<MasterRc> list4 = CacheData.list4;




    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void setting_print() throws IOException, ClassNotFoundException{
        // 직렬화 주석 231018
//        FileMg f = new FileMg();
//        f.list1FileIn();
//        f.list3FileIn();
//        f.list2FileIn();
//        f.list4FileIn();

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
                        System.out.println("\t===========================================================================================");
                        System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                        System.out.println("\t===========================================================================================");

                        // Iterator 활용하여 출력
                        Iterator<Product> itList;
                        itList = list3.iterator();
                        while (itList.hasNext()) {
                            Product itS = itList.next();
                            System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                    itS.getP_stock(), itS.getP_price());
                        }
                        System.out.println("\t===========================================================================================");
                        System.out.println();
                        return;

                    case 2:
                        System.out.println("\n\t[ 2.사장 추천 조합 출력 ]");
                        System.out.println("\t===========================================================================================");
                        System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s || %5s \n",
                                "구분번호", "이름", "칼로리", "금액", "재료", "개수");
                        System.out.println("\t===========================================================================================");

                        // existingList2에 있는 MasterRc 객체를 출력
                        for (MasterRc masterRc : list4) {
                            System.out.printf("\t|| %5d || %5s || %5d || %5d || %5d \n",
                                    masterRc.getR_checkNumber(), masterRc.getR_name(), masterRc.getR_totalcalorie(),
                                    masterRc.getR_price(), masterRc.getR_count());

                            // 재료정보 출력
                            for (Product product : masterRc.getR_products()) {
                                System.out.println("\t재료이름: " + product.getP_name() + " || 재료 갯수: " + product.getP_count());
                            }

                            System.out.println();
                            break;
                        }
                        System.out.println("\t===========================================================================================");
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
                        System.out.println("\n\t[ 1.판매재료 세팅 ]");
                        System.out.print("\t▶ 작업할 대상의 구분번호 입력: ");
                        int pointNumber = Integer.parseInt(br.readLine());
                        boolean found = false;
                        boolean k = false;

                        for (int i = 0; i < list3.size(); i++) {
                            if (pointNumber == list3.get(i).getP_checkNumber()) {
                                k = true;
                                break;
                            }
                        }
                        if (k) {
                            System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                            return;
                        }

                        for (int i = 0; i < list1.size(); i++) {
                            if (pointNumber == list1.get(i).getP_checkNumber()) {
                                found = true;
                                Product selectedProduct = list1.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                                System.out.println("\t===========================================================================================");

                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price());
                                System.out.println("\t===========================================================================================");
                                System.out.println();

                                System.out.print("\t▶ 판매항목에 추가하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // 선택된 항목을 다른 배열에 저장
                                    list3.add(selectedProduct);
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
                        for (int i = 0; i < list4.size(); i++) {
                            if (pointNumber2 == list4.get(i).getR_checkNumber()) {
                                p = true;
                                break;
                            }
                        }
                        if (p) {
                            System.out.println("\t[!] 이미 구분번호가 존재합니다.");
                            return;
                        }

                        for (int i = 0; i < list2.size(); i++) {
                            if (pointNumber2 == list2.get(i).getR_checkNumber()) {
                                found2 = true;
                                MasterRc selectedMasterRc = list2.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "이름", "칼로리", "금액", "재료");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||\n",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t===========================================================================================");

                                System.out.println();
                                System.out.print("\t▶ 판매항목에 추가하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    list4.add(selectedMasterRc);
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

                        for (int i = 0; i < list3.size(); i++) {
                            if (pointNumber == list3.get(i).getP_checkNumber()) {
                                found3 = true;
                                Product selectedProduct = list3.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "분류번호", "이름", "단위", "개수", "칼로리", "적정재고", "금액");
                                System.out.println("\t===========================================================================================");

                                System.out.printf("\t|| %5s || %5s || %5s || %9s || %5s || %5s || %5s || %5s ||\n",
                                        selectedProduct.getP_checkNumber(), selectedProduct.getP_material(), selectedProduct.getP_name(),
                                        selectedProduct.getP_unit(), selectedProduct.getP_count(), selectedProduct.getP_calorie(),
                                        selectedProduct.getP_stock(), selectedProduct.getP_price());
                                System.out.println();
                                System.out.println("\t===========================================================================================");

                                System.out.print("\t▶ 판매항목에 제거하시겠습니까?(Y/N) :");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    // 선택된 항목을 다른 배열에 저장
                                    list3.remove(selectedProduct);
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

                        for (int i = 0; i < list4.size(); i++) {
                            if (pointNumber2 == list4.get(i).getR_checkNumber()) {
                                found4 = true;
                                MasterRc selectedMasterRc = list4.get(i);

                                System.out.println("\t===========================================================================================");
                                System.out.printf("\t|| %5s || %5s || %5s || %5s || %5s ||\n",
                                        "구분번호", "이름", "칼로리", "금액", "재료");
                                System.out.printf("\t|| %5d || %5s || %5d || %5d ||",
                                        selectedMasterRc.getR_checkNumber(), selectedMasterRc.getR_name(),
                                        selectedMasterRc.getR_totalcalorie(), selectedMasterRc.getR_price());
                                System.out.println("\t===========================================================================================");

                                System.out.print("\t▶ 판매항목에 제거하시겠습니까?(Y/N) : ");
                                char x = br.readLine().charAt(0);
                                if (x == 'Y' || x == 'y') {
                                    list4.remove(selectedMasterRc);
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

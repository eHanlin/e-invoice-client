package com.eHanlin.api.invoice.pay2go.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商品明細清單
 */
class ItemList {

    /**
     * 商品單價是否含稅，預設含稅
     */
    private boolean taxIncluded = true;

    /**
     * 銷售額
     */
    private int amt = 0;

    /**
     * 稅額
     */
    private int taxAmt = 0;

    /**
     * 總額
     */
    private int totalAmt = 0;

    /**
     * 使用稅率
     */
    BigDecimal taxRate;

    /**
     * 商品清單
     */
    List<Item> items;

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    ItemList() {
        this.items = new ArrayList<>();
        setTaxRate(InvoiceIssue.NORMAL_TAX_RATE);
    }

    ItemList(boolean taxIncluded) {
        this();
        setTaxIncluded(taxIncluded);
    }

    void setTaxIncluded(boolean taxIncluded) {
        this.taxIncluded = taxIncluded;
        calculate();
    }

    void setTaxRate(int rate) {
        if (rate > 100) {
            throw new IllegalArgumentException("稅率超過100%還是移民吧");
        }

        taxRate = new BigDecimal(rate).divide(ONE_HUNDRED);
        calculate();
    }

    void addItem(int count, String unit, String name, int pricePerUnit) {
        items.add(new Item(count, unit, name, pricePerUnit));
        calculate();
    }

    int size() {
        return items.size();
    }

    String getListCountString() {
        return join((item) -> "" + item.getCount());
    }

    String getListUnitString() {
        return join(Item::getUnit);
    }

    String getListNameString() {
        return join(Item::getName);
    }

    String getListPriceString() {
        return join((item) -> "" + item.getPrice());
    }

    String getListAmtString() {
        return join((item) -> "" + item.getAmt());
    }

    String join(Function<? super Item, ? extends String> mapper) {
        return String.join(Item.SEPARATOR, items.stream().map(mapper).collect(Collectors.toList()));
    }

    /**
     * 發票金額
     */
    int getTotalAmt() {
        return totalAmt;
    }

    /**
     * 稅額
     */
    int getTaxAmt () {
        return taxAmt;
    }

    /**
     * 銷售額
     * @return
     */
    int getAmt() {
        return amt;
    }

    /**
     * 計算 銷售額、稅額、發票金額
     */
    private void calculate() {
        if (taxIncluded) {
            totalAmt = items.stream().mapToInt(Item::getAmt).sum();
            amt = new BigDecimal(totalAmt)
                            .divide(taxRate.add(new BigDecimal("1")), RoundingMode.HALF_UP)
                            .intValue();

            taxAmt = totalAmt - amt;

        } else {
            amt = items.stream().mapToInt(Item::getAmt).sum();
            taxAmt = roundHalfUp(taxRate.multiply(new BigDecimal(amt)));
            totalAmt = amt + taxAmt;
        }
    }

    private int roundHalfUp(BigDecimal number) {
        return number.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    @Override
    public String toString() {
        return null;
    }

    static class Item {

        static final String SEPARATOR = "|";

        int count;

        String unit;

        String name;

        int price;

        Item(int count, String unit, String name, int price) {
            this.count = count;
            this.unit = unit;
            this.name = name;
            this.price = price;
        }

        int getCount() {
            return count;
        }

        String getUnit() {
            return unit;
        }

        String getName() {
            return name;
        }

        int getPrice() {
            return price;
        }

        int getAmt() {
            return count * price;
        }

    }

}

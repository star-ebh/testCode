package com.example.feishu;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {
    public static int countMacAddresses(String startAddress, String endAddress) {
        String[] start = startAddress.split("-");
        String[] end = endAddress.split("-");

        int startInt = Integer.parseInt(start[0], 16);
        int startInt2 = Integer.parseInt(start[1], 16);
        int startInt3 = Integer.parseInt(start[2], 16);
        int startInt4 = Integer.parseInt(start[3], 16);
        int startInt5 = Integer.parseInt(start[4], 16);
        int startInt6 = Integer.parseInt(start[5], 16);

        int endInt = Integer.parseInt(end[0], 16);
        int endInt2 = Integer.parseInt(end[1], 16);
        int endInt3 = Integer.parseInt(end[2], 16);
        int endInt4 = Integer.parseInt(end[3], 16);
        int endInt5 = Integer.parseInt(end[4], 16);
        int endInt6 = Integer.parseInt(end[5], 16);

        int count = 0;
        for (int i = startInt; i <= endInt; i++) {
            for (int j = startInt2; j <= endInt2; j++) {
                for (int k = startInt3; k <= endInt3; k++) {
                    for (int l = startInt4; l <= endInt4; l++) {
                        for (int m = startInt5; m <= endInt5; m++) {
                            for (int n = startInt6; n <= endInt6; n++) {
                                String address = String.format("%02X-%02X-%02X-%02X-%02X-%02X",
                                        i, j, k, l, m, n);
                                if (isMacAddressValid(address)) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean isMacAddressValid(String macAddress) {
        String regex = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
        return macAddress.matches(regex);
    }

    public static void main(String[] args) {
        String startAddress ="40-2C-76-80-00-00";
        String endAddress =  "40-2C-76-8F-FF-FF";
        int count = countMacAddresses(startAddress, endAddress);
        System.out.println("Total number of valid MAC addresses: " + count);
    }
}

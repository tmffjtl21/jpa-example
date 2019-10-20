package com.osstem.jpa.example.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    /**
     * SYSTEM: 시스템
     */
    public static final String SYSTEM = "ROLE_SYSTEM";

    /**
     * ADMIN_MASTER: 마스터관리자
     */
    public static final String ADMIN_MASTER = "ROLE_ADMIN_MASTER";

    /**
     * ADMIN: 관리자
     */
    public static final String ADMIN = "ROLE_ADMIN";

    /**
     * USER: 일반사용자
     */
    public static final String USER = "ROLE_USER";

    /**
     * ANONYMOUS: 권한없음
     */
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    /**
     * EMPLOYEE: 임직원
     */
    public static final String EMPLOYEE = "ROLE_EMPLOYEE";
    
    /**
     * EXECUTIVE: 경영자
     */
    public static final String EXECUTIVE = "ROLE_EXECUTIVE";
    
    /**
     * HR: 인사담당
     */
    public static final String HR = "ROLE_HR";

    /**
     * IT: IT담당
     */
    public static final String IT = "ROLE_IT";

    /**
     * MANAGER: 보직자
     */
    public static final String MANAGER = "ROLE_MANAGER";

    /**
     * MARKETING: 마케팅PM
     */
    public static final String MARKETING = "ROLE_MARKETING";

    /**
     * SALES_REP: 영업대표
     */
    public static final String SALES_REP = "ROLE_SALES_REP";

    /**
     * AR: AR담당
     */
    public static final String AR = "ROLE_AR";

    /**
     * ONLINE: 온라인담당
     */
    public static final String ONLINE = "ROLE_ONLINE";

    /**
     * ORDER_MANAGER: 온라인주문담당자
     */
    public static final String ORDER_MANAGER = "ROLE_ORDER_MANAGER";

    /**
     * LOGISTICS: 물류담당
     */
    public static final String LOGISTICS = "ROLE_LOGISTICS";

    /**
     * DIGITAL_CENTER: 디지털센터(제조법인)
     */
    public static final String DIGITAL_CENTER = "ROLE_DIGITAL_CENTER";

    /**
     * CUSTOMER_MASTER: 거래처 마스터
     */
    public static final String CUSTOMER_MASTER = "ROLE_CUSTOMER_MASTER";
    
    /**
     * CUSTOMER: 거래처
     */
    public static final String CUSTOMER = "ROLE_CUSTOMER";
    
    /**
     * DENTAL_LAB: 기공소
     */
    public static final String DENTAL_LAB = "ROLE_DENTAL_LAB";

    /**
     * CLINIC: 치과
     */
    public static final String CLINIC = "ROLE_CLINIC";

    private AuthoritiesConstants() {
    }
}


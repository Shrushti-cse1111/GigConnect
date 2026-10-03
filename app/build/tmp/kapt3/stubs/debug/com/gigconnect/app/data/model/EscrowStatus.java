package com.gigconnect.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/gigconnect/app/data/model/EscrowStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "AUTHORIZED_HELD", "RELEASED_SETTLED", "REFUNDED", "FROZEN_DISPUTE", "app_debug"})
public enum EscrowStatus {
    /*public static final*/ PENDING /* = new PENDING() */,
    /*public static final*/ AUTHORIZED_HELD /* = new AUTHORIZED_HELD() */,
    /*public static final*/ RELEASED_SETTLED /* = new RELEASED_SETTLED() */,
    /*public static final*/ REFUNDED /* = new REFUNDED() */,
    /*public static final*/ FROZEN_DISPUTE /* = new FROZEN_DISPUTE() */;
    
    EscrowStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.gigconnect.app.data.model.EscrowStatus> getEntries() {
        return null;
    }
}
package com.gigconnect.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b(\b\u0087\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\r\u00a2\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\rH\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0007H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0007H\u00c6\u0003J\t\u0010,\u001a\u00020\u000bH\u00c6\u0003J\t\u0010-\u001a\u00020\rH\u00c6\u0003J\t\u0010.\u001a\u00020\rH\u00c6\u0003J\t\u0010/\u001a\u00020\u0007H\u00c6\u0003J\u0081\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\rH\u00c6\u0001J\u0013\u00101\u001a\u00020\u000b2\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00103\u001a\u00020\u0005H\u00d6\u0001J\t\u00104\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0012\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001cR\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018\u00a8\u00065"}, d2 = {"Lcom/gigconnect/app/data/model/FairMatchCandidate;", "", "worker", "Lcom/gigconnect/app/data/model/Worker;", "skillMatch", "", "distanceKm", "", "currentJobs", "rating", "isRecommended", "", "reason", "", "workerName", "workerRating", "fairMatchScore", "currentWorkload", "idleSince", "(Lcom/gigconnect/app/data/model/Worker;IFIFZLjava/lang/String;Ljava/lang/String;FIILjava/lang/String;)V", "getCurrentJobs", "()I", "getCurrentWorkload", "getDistanceKm", "()F", "getFairMatchScore", "getIdleSince", "()Ljava/lang/String;", "()Z", "getRating", "getReason", "getSkillMatch", "getWorker", "()Lcom/gigconnect/app/data/model/Worker;", "getWorkerName", "getWorkerRating", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class FairMatchCandidate {
    @org.jetbrains.annotations.NotNull()
    private final com.gigconnect.app.data.model.Worker worker = null;
    private final int skillMatch = 0;
    private final float distanceKm = 0.0F;
    private final int currentJobs = 0;
    private final float rating = 0.0F;
    private final boolean isRecommended = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String reason = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String workerName = null;
    private final float workerRating = 0.0F;
    private final int fairMatchScore = 0;
    private final int currentWorkload = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String idleSince = null;
    
    public FairMatchCandidate(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Worker worker, int skillMatch, float distanceKm, int currentJobs, float rating, boolean isRecommended, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    java.lang.String workerName, float workerRating, int fairMatchScore, int currentWorkload, @org.jetbrains.annotations.NotNull()
    java.lang.String idleSince) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.Worker getWorker() {
        return null;
    }
    
    public final int getSkillMatch() {
        return 0;
    }
    
    public final float getDistanceKm() {
        return 0.0F;
    }
    
    public final int getCurrentJobs() {
        return 0;
    }
    
    public final float getRating() {
        return 0.0F;
    }
    
    public final boolean isRecommended() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReason() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWorkerName() {
        return null;
    }
    
    public final float getWorkerRating() {
        return 0.0F;
    }
    
    public final int getFairMatchScore() {
        return 0;
    }
    
    public final int getCurrentWorkload() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIdleSince() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.Worker component1() {
        return null;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final float component9() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.FairMatchCandidate copy(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Worker worker, int skillMatch, float distanceKm, int currentJobs, float rating, boolean isRecommended, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    java.lang.String workerName, float workerRating, int fairMatchScore, int currentWorkload, @org.jetbrains.annotations.NotNull()
    java.lang.String idleSince) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}
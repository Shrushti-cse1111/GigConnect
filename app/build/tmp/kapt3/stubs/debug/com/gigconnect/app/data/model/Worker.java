package com.gigconnect.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b:\b\u0087\b\u0018\u00002\u00020\u0001B\u00e1\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\t\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\t\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u00a2\u0006\u0002\u0010 J\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u0010H\u00c6\u0003J\t\u0010?\u001a\u00020\u0012H\u00c6\u0003J\t\u0010@\u001a\u00020\u0010H\u00c6\u0003J\t\u0010A\u001a\u00020\u0003H\u00c6\u0003J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\u000bH\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u000bH\u00c6\u0003J\t\u0010F\u001a\u00020\tH\u00c6\u0003J\t\u0010G\u001a\u00020\u0010H\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0010H\u00c6\u0003J\t\u0010J\u001a\u00020\u000bH\u00c6\u0003J\t\u0010K\u001a\u00020\u000bH\u00c6\u0003J\t\u0010L\u001a\u00020\u001fH\u00c6\u0003J\u000f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\tH\u00c6\u0003J\t\u0010P\u001a\u00020\u000bH\u00c6\u0003J\t\u0010Q\u001a\u00020\u000bH\u00c6\u0003J\t\u0010R\u001a\u00020\tH\u00c6\u0003J\u000f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\u00fb\u0001\u0010T\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\t2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u00102\b\b\u0002\u0010\u001b\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u00c6\u0001J\u0013\u0010U\u001a\u00020\u00102\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010W\u001a\u00020\u000bH\u00d6\u0001J\t\u0010X\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0016\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u001b\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0018\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u001c\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0019\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010(R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010+R\u0011\u0010\u0013\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010%R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010%R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u001e\u001a\u00020\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010+R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010+R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010+R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010(R\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010+R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010/R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010\u001d\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010\"R\u0011\u0010\u001a\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010%\u00a8\u0006Y"}, d2 = {"Lcom/gigconnect/app/data/model/Worker;", "", "id", "", "name", "skills", "", "primarySkill", "rating", "", "totalReviews", "", "completedJobs", "distanceKm", "languages", "isVerified", "", "verificationStatus", "Lcom/gigconnect/app/data/model/VerificationStatus;", "isAvailable", "profileImageUrl", "experience", "basePrice", "serviceArea", "currentWorkload", "fairRotationScore", "welfareEnrolled", "cooperativeMember", "equityBalance", "weeklyEarnings", "location", "Lcom/gigconnect/app/data/model/Location;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;FIIFLjava/util/List;ZLcom/gigconnect/app/data/model/VerificationStatus;ZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;IFZZIILcom/gigconnect/app/data/model/Location;)V", "getBasePrice", "()I", "getCompletedJobs", "getCooperativeMember", "()Z", "getCurrentWorkload", "getDistanceKm", "()F", "getEquityBalance", "getExperience", "()Ljava/lang/String;", "getFairRotationScore", "getId", "getLanguages", "()Ljava/util/List;", "getLocation", "()Lcom/gigconnect/app/data/model/Location;", "getName", "getPrimarySkill", "getProfileImageUrl", "getRating", "getServiceArea", "getSkills", "getTotalReviews", "getVerificationStatus", "()Lcom/gigconnect/app/data/model/VerificationStatus;", "getWeeklyEarnings", "getWelfareEnrolled", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class Worker {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> skills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String primarySkill = null;
    private final float rating = 0.0F;
    private final int totalReviews = 0;
    private final int completedJobs = 0;
    private final float distanceKm = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> languages = null;
    private final boolean isVerified = false;
    @org.jetbrains.annotations.NotNull()
    private final com.gigconnect.app.data.model.VerificationStatus verificationStatus = null;
    private final boolean isAvailable = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String profileImageUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String experience = null;
    private final int basePrice = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String serviceArea = null;
    private final int currentWorkload = 0;
    private final float fairRotationScore = 0.0F;
    private final boolean welfareEnrolled = false;
    private final boolean cooperativeMember = false;
    private final int equityBalance = 0;
    private final int weeklyEarnings = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.gigconnect.app.data.model.Location location = null;
    
    public Worker(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> skills, @org.jetbrains.annotations.NotNull()
    java.lang.String primarySkill, float rating, int totalReviews, int completedJobs, float distanceKm, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> languages, boolean isVerified, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.VerificationStatus verificationStatus, boolean isAvailable, @org.jetbrains.annotations.NotNull()
    java.lang.String profileImageUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String experience, int basePrice, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceArea, int currentWorkload, float fairRotationScore, boolean welfareEnrolled, boolean cooperativeMember, int equityBalance, int weeklyEarnings, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Location location) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPrimarySkill() {
        return null;
    }
    
    public final float getRating() {
        return 0.0F;
    }
    
    public final int getTotalReviews() {
        return 0;
    }
    
    public final int getCompletedJobs() {
        return 0;
    }
    
    public final float getDistanceKm() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLanguages() {
        return null;
    }
    
    public final boolean isVerified() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.VerificationStatus getVerificationStatus() {
        return null;
    }
    
    public final boolean isAvailable() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProfileImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExperience() {
        return null;
    }
    
    public final int getBasePrice() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getServiceArea() {
        return null;
    }
    
    public final int getCurrentWorkload() {
        return 0;
    }
    
    public final float getFairRotationScore() {
        return 0.0F;
    }
    
    public final boolean getWelfareEnrolled() {
        return false;
    }
    
    public final boolean getCooperativeMember() {
        return false;
    }
    
    public final int getEquityBalance() {
        return 0;
    }
    
    public final int getWeeklyEarnings() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.Location getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.VerificationStatus component11() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    public final int component15() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    public final int component17() {
        return 0;
    }
    
    public final float component18() {
        return 0.0F;
    }
    
    public final boolean component19() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final boolean component20() {
        return false;
    }
    
    public final int component21() {
        return 0;
    }
    
    public final int component22() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.Location component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.Worker copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> skills, @org.jetbrains.annotations.NotNull()
    java.lang.String primarySkill, float rating, int totalReviews, int completedJobs, float distanceKm, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> languages, boolean isVerified, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.VerificationStatus verificationStatus, boolean isAvailable, @org.jetbrains.annotations.NotNull()
    java.lang.String profileImageUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String experience, int basePrice, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceArea, int currentWorkload, float fairRotationScore, boolean welfareEnrolled, boolean cooperativeMember, int equityBalance, int weeklyEarnings, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Location location) {
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
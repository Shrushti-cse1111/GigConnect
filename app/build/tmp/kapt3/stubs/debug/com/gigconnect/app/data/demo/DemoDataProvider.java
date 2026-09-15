package com.gigconnect.app.data.demo;

/**
 * GigConnect Demo Data Provider
 * Provides realistic, location-aware mock data for all 12 Indian cities.
 * All data is clearly labeled as DEMO DATA.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\f2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010A\u001a\u00020BJ\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\fJ\u0010\u0010E\u001a\u0004\u0018\u00010\r2\u0006\u0010F\u001a\u00020BJ\u0014\u0010G\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010H\u001a\u00020BJ\u001c\u0010I\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010H\u001a\u00020B2\u0006\u0010A\u001a\u00020BR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000fR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u000fR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000fR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000fR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u000fR\u0011\u0010(\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\nR\u001a\u0010*\u001a\u00020\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u0002000\f\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u000fR\u0017\u00102\u001a\b\u0012\u0004\u0012\u0002030\f\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u000fR\u0017\u00105\u001a\b\u0012\u0004\u0012\u0002060\f\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u000fR\u0011\u00108\u001a\u000209\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010\n\u00a8\u0006J"}, d2 = {"Lcom/gigconnect/app/data/demo/DemoDataProvider;", "", "()V", "adminStats", "Lcom/gigconnect/app/data/model/AdminStats;", "getAdminStats", "()Lcom/gigconnect/app/data/model/AdminStats;", "adminUser", "Lcom/gigconnect/app/data/model/User;", "getAdminUser", "()Lcom/gigconnect/app/data/model/User;", "bengaluruWorkers", "", "Lcom/gigconnect/app/data/model/Worker;", "getBengaluruWorkers", "()Ljava/util/List;", "delhiWorkers", "getDelhiWorkers", "demandForecasts", "Lcom/gigconnect/app/data/model/DemandForecast;", "getDemandForecasts", "demoBookings", "Lcom/gigconnect/app/data/model/Booking;", "getDemoBookings", "demoCities", "Lcom/gigconnect/app/data/model/DemoCity;", "getDemoCities", "demoJobs", "Lcom/gigconnect/app/data/model/Job;", "getDemoJobs", "equityEntries", "Lcom/gigconnect/app/data/model/EquityEntry;", "getEquityEntries", "grievanceCases", "Lcom/gigconnect/app/data/model/GrievanceCase;", "getGrievanceCases", "hyderabadWorkers", "getHyderabadWorkers", "puneWorkers", "getPuneWorkers", "seekerUser", "getSeekerUser", "selectedCity", "getSelectedCity", "()Lcom/gigconnect/app/data/model/DemoCity;", "setSelectedCity", "(Lcom/gigconnect/app/data/model/DemoCity;)V", "serviceCategories", "Lcom/gigconnect/app/data/model/ServiceCategory;", "getServiceCategories", "skillRecommendations", "Lcom/gigconnect/app/data/model/SkillRecommendation;", "getSkillRecommendations", "votingProposals", "Lcom/gigconnect/app/data/model/VotingProposal;", "getVotingProposals", "welfareStats", "Lcom/gigconnect/app/data/model/WelfareStats;", "getWelfareStats", "()Lcom/gigconnect/app/data/model/WelfareStats;", "workerUser", "getWorkerUser", "computeSmartMatch", "Lcom/gigconnect/app/data/model/SmartMatchResult;", "workers", "serviceId", "", "getFairMatchCandidates", "Lcom/gigconnect/app/data/model/FairMatchCandidate;", "getWorkerById", "id", "getWorkersForCity", "cityName", "getWorkersForService", "app_debug"})
public final class DemoDataProvider {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.DemoCity> demoCities = null;
    @org.jetbrains.annotations.NotNull()
    private static com.gigconnect.app.data.model.DemoCity selectedCity;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.ServiceCategory> serviceCategories = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.Worker> puneWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.Worker> bengaluruWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.Worker> delhiWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.Worker> hyderabadWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.Booking> demoBookings = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.Job> demoJobs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.EquityEntry> equityEntries = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.SkillRecommendation> skillRecommendations = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.gigconnect.app.data.model.AdminStats adminStats = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.DemandForecast> demandForecasts = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.gigconnect.app.data.model.WelfareStats welfareStats = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.GrievanceCase> grievanceCases = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.gigconnect.app.data.model.VotingProposal> votingProposals = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.gigconnect.app.data.model.User seekerUser = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.gigconnect.app.data.model.User workerUser = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.gigconnect.app.data.model.User adminUser = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.gigconnect.app.data.demo.DemoDataProvider INSTANCE = null;
    
    private DemoDataProvider() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.DemoCity> getDemoCities() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.DemoCity getSelectedCity() {
        return null;
    }
    
    public final void setSelectedCity(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.DemoCity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.ServiceCategory> getServiceCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Worker> getPuneWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Worker> getBengaluruWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Worker> getDelhiWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Worker> getHyderabadWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.gigconnect.app.data.model.Worker getWorkerById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Worker> getWorkersForCity(@org.jetbrains.annotations.NotNull()
    java.lang.String cityName) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Worker> getWorkersForService(@org.jetbrains.annotations.NotNull()
    java.lang.String cityName, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.SmartMatchResult> computeSmartMatch(@org.jetbrains.annotations.NotNull()
    java.util.List<com.gigconnect.app.data.model.Worker> workers, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.FairMatchCandidate> getFairMatchCandidates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Booking> getDemoBookings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.Job> getDemoJobs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.EquityEntry> getEquityEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.SkillRecommendation> getSkillRecommendations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.AdminStats getAdminStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.DemandForecast> getDemandForecasts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.WelfareStats getWelfareStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.GrievanceCase> getGrievanceCases() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.VotingProposal> getVotingProposals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.User getSeekerUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.User getWorkerUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.User getAdminUser() {
        return null;
    }
}
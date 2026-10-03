package com.gigconnect.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u001b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0006J\u0018\u0010J\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00062\b\b\u0002\u0010K\u001a\u00020\u0006J\u000e\u0010L\u001a\u00020H2\u0006\u0010M\u001a\u00020\u0006J\u0010\u0010N\u001a\u0004\u0018\u00010\u00102\u0006\u0010O\u001a\u00020\u0006J\u000e\u0010P\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0006J\u000e\u0010Q\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0006J\u0016\u0010R\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00062\u0006\u0010S\u001a\u00020\u0006J\u000e\u0010T\u001a\u00020H2\u0006\u0010U\u001a\u00020\u0010J\u0010\u0010V\u001a\u00020H2\b\b\u0002\u0010W\u001a\u00020\u0006J\u000e\u0010X\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0006J\u000e\u0010Y\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0006J\u000e\u0010Z\u001a\u00020H2\u0006\u0010[\u001a\u00020\u0006J\u0006\u0010\\\u001a\u00020HJ\u0016\u0010]\u001a\u00020H2\u0006\u0010M\u001a\u00020\u00062\u0006\u0010^\u001a\u00020\tJ\u000e\u0010_\u001a\u00020H2\u0006\u0010`\u001a\u00020\tJ\u000e\u0010a\u001a\u00020H2\u0006\u0010b\u001a\u00020\u0006R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0 \u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0 \u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120 \u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100 \u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\"R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\"R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010\"R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\"R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020\u00060 \u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\"R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\"R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\"R\u001d\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00050 \u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010\"R\u0011\u0010A\u001a\u00020B\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00060 \u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010\"\u00a8\u0006c"}, d2 = {"Lcom/gigconnect/app/viewmodel/WorkerViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_certifiedSkills", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_detectedLanguage", "_equityBalance", "", "_equityEntries", "Lcom/gigconnect/app/data/model/EquityEntry;", "_isAvailable", "", "_isVoiceListening", "_jobs", "Lcom/gigconnect/app/data/model/Job;", "_kycInfo", "Lcom/gigconnect/app/data/model/WorkerKycInfo;", "_selectedJob", "_serviceRadiusKm", "_skillRecommendations", "Lcom/gigconnect/app/data/model/SkillRecommendation;", "_thisMonthEquity", "_todayJobs", "_voiceResult", "_weeklyEarnings", "_welfareBalance", "_welfareClaims", "Lcom/gigconnect/app/data/model/WelfareClaim;", "_workingHours", "certifiedSkills", "Lkotlinx/coroutines/flow/StateFlow;", "getCertifiedSkills", "()Lkotlinx/coroutines/flow/StateFlow;", "detectedLanguage", "getDetectedLanguage", "equityBalance", "getEquityBalance", "equityEntries", "getEquityEntries", "isAvailable", "isVoiceListening", "jobs", "getJobs", "kycInfo", "getKycInfo", "selectedJob", "getSelectedJob", "serviceRadiusKm", "getServiceRadiusKm", "skillRecommendations", "getSkillRecommendations", "thisMonthEquity", "getThisMonthEquity", "todayJobs", "getTodayJobs", "voiceResult", "getVoiceResult", "weeklyEarnings", "getWeeklyEarnings", "welfareBalance", "getWelfareBalance", "welfareClaims", "getWelfareClaims", "worker", "Lcom/gigconnect/app/data/model/Worker;", "getWorker", "()Lcom/gigconnect/app/data/model/Worker;", "workingHours", "getWorkingHours", "acceptJob", "", "jobId", "completeJob", "notes", "completeSkillTraining", "skillName", "getJobById", "id", "markArrived", "rejectJob", "rejectJobWithReason", "reason", "selectJob", "job", "simulateVoiceInput", "language", "startJob", "startTransit", "submitKycDocument", "docType", "toggleAvailability", "updateCourseProgress", "progress", "updateServiceRadius", "radiusKm", "updateWorkingHours", "hours", "app_debug"})
public final class WorkerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.Job>> _jobs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Job>> jobs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.Job> _selectedJob = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.Job> selectedJob = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isAvailable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAvailable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _weeklyEarnings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> weeklyEarnings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _todayJobs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> todayJobs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _equityBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> equityBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _thisMonthEquity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> thisMonthEquity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.EquityEntry>> _equityEntries = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.EquityEntry>> equityEntries = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.SkillRecommendation>> _skillRecommendations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.SkillRecommendation>> skillRecommendations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isVoiceListening = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isVoiceListening = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _voiceResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> voiceResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _detectedLanguage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> detectedLanguage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _welfareBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> welfareBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.WelfareClaim>> _welfareClaims = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.WelfareClaim>> welfareClaims = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.WorkerKycInfo> _kycInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.WorkerKycInfo> kycInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _certifiedSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> certifiedSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _serviceRadiusKm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> serviceRadiusKm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _workingHours = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> workingHours = null;
    @org.jetbrains.annotations.NotNull()
    private final com.gigconnect.app.data.model.Worker worker = null;
    
    public WorkerViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Job>> getJobs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.Job> getSelectedJob() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAvailable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getWeeklyEarnings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTodayJobs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getEquityBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getThisMonthEquity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.EquityEntry>> getEquityEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.SkillRecommendation>> getSkillRecommendations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isVoiceListening() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getVoiceResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getDetectedLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getWelfareBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.WelfareClaim>> getWelfareClaims() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.WorkerKycInfo> getKycInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getCertifiedSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getServiceRadiusKm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getWorkingHours() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.gigconnect.app.data.model.Worker getWorker() {
        return null;
    }
    
    public final void toggleAvailability() {
    }
    
    public final void updateServiceRadius(int radiusKm) {
    }
    
    public final void updateWorkingHours(@org.jetbrains.annotations.NotNull()
    java.lang.String hours) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.gigconnect.app.data.model.Job getJobById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void selectJob(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Job job) {
    }
    
    public final void acceptJob(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId) {
    }
    
    public final void rejectJobWithReason(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId, @org.jetbrains.annotations.NotNull()
    java.lang.String reason) {
    }
    
    public final void rejectJob(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId) {
    }
    
    public final void startTransit(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId) {
    }
    
    public final void markArrived(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId) {
    }
    
    public final void startJob(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId) {
    }
    
    public final void completeJob(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
    }
    
    public final void completeSkillTraining(@org.jetbrains.annotations.NotNull()
    java.lang.String skillName) {
    }
    
    public final void updateCourseProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String skillName, int progress) {
    }
    
    public final void submitKycDocument(@org.jetbrains.annotations.NotNull()
    java.lang.String docType) {
    }
    
    public final void simulateVoiceInput(@org.jetbrains.annotations.NotNull()
    java.lang.String language) {
    }
}
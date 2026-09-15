package com.gigconnect.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+J\u000e\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020\fJ\u0016\u0010/\u001a\u00020)2\u0006\u00100\u001a\u00020+2\u0006\u00101\u001a\u000202R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00050\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\"\u001a\u00020#\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001b\u00a8\u00063"}, d2 = {"Lcom/gigconnect/app/viewmodel/AdminViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_fairMatchCandidates", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/gigconnect/app/data/model/FairMatchCandidate;", "_grievances", "Lcom/gigconnect/app/data/model/GrievanceCase;", "_proposals", "Lcom/gigconnect/app/data/model/VotingProposal;", "_workerFilter", "Lcom/gigconnect/app/data/model/VerificationStatus;", "adminStats", "Lcom/gigconnect/app/data/model/AdminStats;", "getAdminStats", "()Lcom/gigconnect/app/data/model/AdminStats;", "allWorkers", "Lcom/gigconnect/app/data/model/Worker;", "getAllWorkers", "()Ljava/util/List;", "demandForecasts", "Lcom/gigconnect/app/data/model/DemandForecast;", "getDemandForecasts", "fairMatchCandidates", "Lkotlinx/coroutines/flow/StateFlow;", "getFairMatchCandidates", "()Lkotlinx/coroutines/flow/StateFlow;", "filteredWorkers", "getFilteredWorkers", "grievances", "getGrievances", "proposals", "getProposals", "welfareStats", "Lcom/gigconnect/app/data/model/WelfareStats;", "getWelfareStats", "()Lcom/gigconnect/app/data/model/WelfareStats;", "workerFilter", "getWorkerFilter", "raiseGrievance", "", "category", "", "description", "setWorkerFilter", "status", "vote", "proposalId", "choice", "Lcom/gigconnect/app/data/model/VoteChoice;", "app_debug"})
public final class AdminViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.gigconnect.app.data.model.AdminStats adminStats = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.gigconnect.app.data.model.DemandForecast> demandForecasts = null;
    @org.jetbrains.annotations.NotNull()
    private final com.gigconnect.app.data.model.WelfareStats welfareStats = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.gigconnect.app.data.model.Worker> allWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.GrievanceCase>> _grievances = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.GrievanceCase>> grievances = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.VotingProposal>> _proposals = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.VotingProposal>> proposals = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.FairMatchCandidate>> _fairMatchCandidates = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.FairMatchCandidate>> fairMatchCandidates = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.VerificationStatus> _workerFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.VerificationStatus> workerFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Worker>> filteredWorkers = null;
    
    public AdminViewModel() {
        super();
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
    public final java.util.List<com.gigconnect.app.data.model.Worker> getAllWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.GrievanceCase>> getGrievances() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.VotingProposal>> getProposals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.FairMatchCandidate>> getFairMatchCandidates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.VerificationStatus> getWorkerFilter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Worker>> getFilteredWorkers() {
        return null;
    }
    
    public final void setWorkerFilter(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.VerificationStatus status) {
    }
    
    public final void vote(@org.jetbrains.annotations.NotNull()
    java.lang.String proposalId, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.VoteChoice choice) {
    }
    
    public final void raiseGrievance(@org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String description) {
    }
}
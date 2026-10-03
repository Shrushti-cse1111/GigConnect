package com.gigconnect.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u00152\b\b\u0002\u0010T\u001a\u00020\u0010J8\u0010U\u001a\u00020R2\b\b\u0002\u0010V\u001a\u00020\u00152\b\b\u0002\u0010W\u001a\u00020\u00152\b\b\u0002\u0010X\u001a\u00020\u00152\b\b\u0002\u0010Y\u001a\u00020\u00152\b\b\u0002\u0010Z\u001a\u00020\u0015J\u000e\u0010[\u001a\u00020R2\u0006\u0010\\\u001a\u00020\u0015J\u0016\u0010]\u001a\u00020R2\u0006\u0010^\u001a\u00020\u001b2\u0006\u0010_\u001a\u00020\u0015J\u000e\u0010`\u001a\u00020R2\u0006\u0010a\u001a\u00020\nJ\u0018\u0010b\u001a\u00020R2\u0006\u0010_\u001a\u00020\u00152\b\b\u0002\u0010c\u001a\u00020\u0015J.\u0010d\u001a\u00020R2\u0006\u0010\\\u001a\u00020\u00152\u0006\u0010^\u001a\u00020e2\u0006\u0010f\u001a\u00020\u00152\u000e\b\u0002\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00150\tJ\u000e\u0010h\u001a\u00020R2\u0006\u0010i\u001a\u00020\u0015J\u0006\u0010j\u001a\u00020RJ\u0016\u0010k\u001a\u00020R2\u0006\u0010l\u001a\u00020\u001b2\u0006\u0010_\u001a\u00020\u0015J\u000e\u0010m\u001a\u00020R2\u0006\u0010n\u001a\u00020\u001dJ\u000e\u0010o\u001a\u00020R2\u0006\u0010p\u001a\u00020\u0015J\u000e\u0010q\u001a\u00020R2\u0006\u0010r\u001a\u00020\u0007J\u000e\u0010s\u001a\u00020R2\u0006\u0010n\u001a\u00020\u001dJ\b\u0010t\u001a\u00020RH\u0002J,\u0010u\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u00152\u0006\u0010v\u001a\u00020$2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u00150\t2\u0006\u0010x\u001a\u00020\u0015J\u000e\u0010y\u001a\u00020R2\u0006\u0010z\u001a\u00020\u0015J&\u0010{\u001a\u00020R2\u0006\u0010V\u001a\u00020\u00152\u0006\u0010|\u001a\u00020\u00152\u0006\u0010}\u001a\u00020\u00152\u0006\u0010~\u001a\u00020\u0015J\u001f\u0010\u007f\u001a\u00020R2\u0006\u0010W\u001a\u00020\u00152\u0006\u0010X\u001a\u00020\u00152\u0007\u0010\u0080\u0001\u001a\u00020\u0010J\u0010\u0010\u0081\u0001\u001a\u00020R2\u0007\u0010\u0082\u0001\u001a\u00020\u0015J\u0017\u0010\u0083\u0001\u001a\u00020\u00102\u0006\u0010_\u001a\u00020\u00152\u0006\u0010~\u001a\u00020\u0015R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010!\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\"0\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\'\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\'\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010)R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010)R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010)R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\'\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010)R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\u00100\'\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010)R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u00100\'\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010)R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\'\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010)R\u0019\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\'\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010)R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00190\'\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010)R\u0019\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010)R\u0019\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010)R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020\u001b0\t\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001f0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010)R\u001d\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\t0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010)R)\u0010I\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100\"0\t0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010)R\u0017\u0010K\u001a\b\u0012\u0004\u0012\u00020$0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u0010)R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\t0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u0010)\u00a8\u0006\u0084\u0001"}, d2 = {"Lcom/gigconnect/app/viewmodel/SeekerViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_activeDispute", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/gigconnect/app/data/model/DisputeRecord;", "_bookingStatus", "Lcom/gigconnect/app/data/model/BookingStatus;", "_bookings", "", "Lcom/gigconnect/app/data/model/Booking;", "_currentBooking", "_disputes", "_escrowStatus", "Lcom/gigconnect/app/data/model/EscrowStatus;", "_isLoadingWorkers", "", "_isSmartMatchRunning", "_locationSharingConsentGranted", "_ratedBookingIds", "", "", "_recommendedWorker", "Lcom/gigconnect/app/data/model/SmartMatchResult;", "_requestDraft", "Lcom/gigconnect/app/data/model/ServiceRequestDraft;", "_selectedService", "Lcom/gigconnect/app/data/model/ServiceCategory;", "_selectedWorker", "Lcom/gigconnect/app/data/model/Worker;", "_smartMatchProgress", "", "_smartMatchResults", "_smartMatchSteps", "Lkotlin/Pair;", "_trackingEta", "", "_workers", "activeDispute", "Lkotlinx/coroutines/flow/StateFlow;", "getActiveDispute", "()Lkotlinx/coroutines/flow/StateFlow;", "bookingStatus", "getBookingStatus", "bookings", "getBookings", "currentBooking", "getCurrentBooking", "disputes", "getDisputes", "escrowStatus", "getEscrowStatus", "isLoadingWorkers", "isSmartMatchRunning", "locationSharingConsentGranted", "getLocationSharingConsentGranted", "ratedBookingIds", "getRatedBookingIds", "recommendedWorker", "getRecommendedWorker", "requestDraft", "getRequestDraft", "selectedService", "getSelectedService", "selectedWorker", "getSelectedWorker", "serviceCategories", "getServiceCategories", "()Ljava/util/List;", "smartMatchProgress", "getSmartMatchProgress", "smartMatchResults", "getSmartMatchResults", "smartMatchSteps", "getSmartMatchSteps", "trackingEta", "getTrackingEta", "trackingJob", "Lkotlinx/coroutines/Job;", "workers", "getWorkers", "addDraftMedia", "", "name", "isVideo", "confirmBooking", "address", "date", "time", "instructions", "paymentMethod", "confirmCompletionAndReleaseEscrow", "bookingId", "initServiceRequest", "category", "cityName", "loadBookingForTracking", "booking", "loadWorkers", "serviceId", "raiseDispute", "Lcom/gigconnect/app/data/model/DisputeCategory;", "description", "evidencePhotos", "removeDraftMedia", "mediaId", "runSmartMatch", "selectService", "service", "selectWorker", "worker", "selectWorkerById", "workerId", "setBookingStatus", "status", "setSelectedWorker", "simulateTracking", "submitReview", "rating", "tags", "comment", "updateDraftDescription", "desc", "updateDraftLocation", "locality", "city", "pincode", "updateDraftSchedule", "isInstant", "updateDraftUrgency", "urgency", "validateServiceArea", "app_debug"})
public final class SeekerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.ServiceRequestDraft> _requestDraft = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.ServiceRequestDraft> requestDraft = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.Worker>> _workers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Worker>> workers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoadingWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoadingWorkers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.ServiceCategory> _selectedService = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.ServiceCategory> selectedService = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.Worker> _selectedWorker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.Worker> selectedWorker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.SmartMatchResult>> _smartMatchResults = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.SmartMatchResult>> smartMatchResults = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isSmartMatchRunning = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSmartMatchRunning = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Float> _smartMatchProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Float> smartMatchProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<kotlin.Pair<java.lang.String, java.lang.Boolean>>> _smartMatchSteps = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<kotlin.Pair<java.lang.String, java.lang.Boolean>>> smartMatchSteps = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.SmartMatchResult> _recommendedWorker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.SmartMatchResult> recommendedWorker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.Booking> _currentBooking = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.Booking> currentBooking = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.Booking>> _bookings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Booking>> bookings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.BookingStatus> _bookingStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.BookingStatus> bookingStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.EscrowStatus> _escrowStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.EscrowStatus> escrowStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _trackingEta = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> trackingEta = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _locationSharingConsentGranted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> locationSharingConsentGranted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.gigconnect.app.data.model.DisputeRecord>> _disputes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.DisputeRecord>> disputes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.DisputeRecord> _activeDispute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.DisputeRecord> activeDispute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _ratedBookingIds = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> ratedBookingIds = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job trackingJob;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.gigconnect.app.data.model.ServiceCategory> serviceCategories = null;
    
    public SeekerViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.ServiceRequestDraft> getRequestDraft() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Worker>> getWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoadingWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.ServiceCategory> getSelectedService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.Worker> getSelectedWorker() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.SmartMatchResult>> getSmartMatchResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSmartMatchRunning() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Float> getSmartMatchProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<kotlin.Pair<java.lang.String, java.lang.Boolean>>> getSmartMatchSteps() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.SmartMatchResult> getRecommendedWorker() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.Booking> getCurrentBooking() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.Booking>> getBookings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.BookingStatus> getBookingStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.EscrowStatus> getEscrowStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTrackingEta() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getLocationSharingConsentGranted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.gigconnect.app.data.model.DisputeRecord>> getDisputes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.DisputeRecord> getActiveDispute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> getRatedBookingIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.ServiceCategory> getServiceCategories() {
        return null;
    }
    
    public final void initServiceRequest(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.ServiceCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String cityName) {
    }
    
    public final void updateDraftDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String desc) {
    }
    
    public final void updateDraftUrgency(@org.jetbrains.annotations.NotNull()
    java.lang.String urgency) {
    }
    
    public final void addDraftMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isVideo) {
    }
    
    public final void removeDraftMedia(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaId) {
    }
    
    public final void updateDraftLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String locality, @org.jetbrains.annotations.NotNull()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    java.lang.String pincode) {
    }
    
    public final void updateDraftSchedule(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String time, boolean isInstant) {
    }
    
    public final boolean validateServiceArea(@org.jetbrains.annotations.NotNull()
    java.lang.String cityName, @org.jetbrains.annotations.NotNull()
    java.lang.String pincode) {
        return false;
    }
    
    public final void selectService(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.ServiceCategory service, @org.jetbrains.annotations.NotNull()
    java.lang.String cityName) {
    }
    
    public final void selectWorker(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Worker worker) {
    }
    
    public final void setSelectedWorker(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Worker worker) {
    }
    
    public final void selectWorkerById(@org.jetbrains.annotations.NotNull()
    java.lang.String workerId) {
    }
    
    public final void loadWorkers(@org.jetbrains.annotations.NotNull()
    java.lang.String cityName, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceId) {
    }
    
    public final void runSmartMatch() {
    }
    
    public final void confirmBooking(@org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String time, @org.jetbrains.annotations.NotNull()
    java.lang.String instructions, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod) {
    }
    
    public final void loadBookingForTracking(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Booking booking) {
    }
    
    private final void simulateTracking() {
    }
    
    public final void setBookingStatus(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.BookingStatus status) {
    }
    
    public final void confirmCompletionAndReleaseEscrow(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId) {
    }
    
    public final void raiseDispute(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.DisputeCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> evidencePhotos) {
    }
    
    public final boolean submitReview(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId, int rating, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    java.lang.String comment) {
        return false;
    }
}
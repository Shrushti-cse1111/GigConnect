package com.gigconnect.app.ui.seeker;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001e\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a6\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a,\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a \u0010\u0017\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0003\u001a\u001e\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0016\u0010\u001b\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a*\u0010\u001c\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u0011H\u0003\u00a8\u0006\u001f"}, d2 = {"BookingConfirmationScreen", "", "seekerViewModel", "Lcom/gigconnect/app/viewmodel/SeekerViewModel;", "onTrack", "Lkotlin/Function0;", "onHome", "BookingsListScreen", "onBack", "LiveTrackingScreen", "onPayment", "PaymentMethodOption", "title", "", "subtitle", "icon", "isSelected", "", "onSelect", "PaymentScreen", "onSuccess", "PaymentSuccessScreen", "onRate", "ProfileSettingItem", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RatingScreen", "onDone", "SeekerProfileScreen", "TrackingStepItem", "isDone", "isCurrent", "app_debug"})
public final class BookingFlowScreensKt {
    
    @androidx.compose.runtime.Composable()
    public static final void BookingConfirmationScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onTrack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onHome) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LiveTrackingScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPayment, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TrackingStepItem(java.lang.String title, java.lang.String subtitle, boolean isDone, boolean isCurrent) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PaymentScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PaymentMethodOption(java.lang.String title, java.lang.String subtitle, java.lang.String icon, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PaymentSuccessScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onHome) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RatingScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BookingsListScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SeekerProfileScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProfileSettingItem(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String title, java.lang.String subtitle) {
    }
}
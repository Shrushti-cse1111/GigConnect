package com.gigconnect.app.ui.seeker;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001aH\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\t\u001a\u00020\nH\u0007\u001a\u008e\u0001\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0007\u00a8\u0006\u001c"}, d2 = {"EmergencyCard", "", "onClick", "Lkotlin/Function0;", "SeekerBottomNav", "onHome", "onBookings", "onMessages", "onProfile", "selected", "", "SeekerHomeScreen", "cityName", "", "seekerViewModel", "Lcom/gigconnect/app/viewmodel/SeekerViewModel;", "onServiceSelected", "Lkotlin/Function1;", "Lcom/gigconnect/app/data/model/ServiceCategory;", "onWorkerTapped", "Lcom/gigconnect/app/data/model/Worker;", "onBookingsClick", "onMessagesClick", "onProfileClick", "onRoleSwitchClick", "onLocationClick", "isOffline", "", "app_debug"})
public final class SeekerHomeScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SeekerHomeScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String cityName, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.SeekerViewModel seekerViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.gigconnect.app.data.model.ServiceCategory, kotlin.Unit> onServiceSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.gigconnect.app.data.model.Worker, kotlin.Unit> onWorkerTapped, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBookingsClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMessagesClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProfileClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRoleSwitchClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLocationClick, boolean isOffline) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmergencyCard(kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SeekerBottomNav(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onHome, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBookings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMessages, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onProfile, int selected) {
    }
}
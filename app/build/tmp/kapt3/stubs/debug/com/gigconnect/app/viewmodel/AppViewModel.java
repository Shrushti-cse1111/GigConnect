package com.gigconnect.app.viewmodel;

/**
 * App-level ViewModel shared across all screens.
 * Controls role switching, demo location, and connectivity simulation.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020 J\u000e\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u000bJ\u000e\u0010\"\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0005J\u000e\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0007J\u0006\u0010\u001b\u001a\u00020 J\u0006\u0010\u001d\u001a\u00020 J\u0006\u0010\'\u001a\u00020 R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0011\u00a8\u0006("}, d2 = {"Lcom/gigconnect/app/viewmodel/AppViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_currentCity", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_currentRole", "Lcom/gigconnect/app/data/model/UserRole;", "_isOffline", "", "_selectedCity", "Lcom/gigconnect/app/data/model/DemoCity;", "_showLocationSwitcher", "_showRoleSwitcher", "currentCity", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentCity", "()Lkotlinx/coroutines/flow/StateFlow;", "currentRole", "getCurrentRole", "demoCities", "", "getDemoCities", "()Ljava/util/List;", "isOffline", "selectedCity", "getSelectedCity", "showLocationSwitcher", "getShowLocationSwitcher", "showRoleSwitcher", "getShowRoleSwitcher", "hideLocationSwitcher", "", "hideRoleSwitcher", "setCity", "city", "cityName", "setRole", "role", "toggleOffline", "app_debug"})
public final class AppViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.UserRole> _currentRole = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.UserRole> currentRole = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.gigconnect.app.data.model.DemoCity> _selectedCity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.DemoCity> selectedCity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _currentCity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> currentCity = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isOffline = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOffline = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showRoleSwitcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showRoleSwitcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showLocationSwitcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showLocationSwitcher = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.gigconnect.app.data.model.DemoCity> demoCities = null;
    
    public AppViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.UserRole> getCurrentRole() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.gigconnect.app.data.model.DemoCity> getSelectedCity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getCurrentCity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOffline() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowRoleSwitcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowLocationSwitcher() {
        return null;
    }
    
    public final void setRole(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.UserRole role) {
    }
    
    public final void setCity(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.DemoCity city) {
    }
    
    public final void setCity(@org.jetbrains.annotations.NotNull()
    java.lang.String cityName) {
    }
    
    public final void toggleOffline() {
    }
    
    public final void showRoleSwitcher() {
    }
    
    public final void hideRoleSwitcher() {
    }
    
    public final void showLocationSwitcher() {
    }
    
    public final void hideLocationSwitcher() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.gigconnect.app.data.model.DemoCity> getDemoCities() {
        return null;
    }
}
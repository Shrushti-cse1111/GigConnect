package com.gigconnect.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a@\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a2\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001aH\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"DemoLocationSwitcher", "", "cities", "", "Lcom/gigconnect/app/data/model/DemoCity;", "selectedCity", "onCitySelected", "Lkotlin/Function1;", "onDismiss", "Lkotlin/Function0;", "DemoRoleSwitcher", "currentRole", "Lcom/gigconnect/app/data/model/UserRole;", "onRoleSelected", "RoleSwitcherCard", "emoji", "", "title", "subtitle", "isSelected", "", "color", "Landroidx/compose/ui/graphics/Color;", "onClick", "RoleSwitcherCard-jzV_Hc0", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJLkotlin/jvm/functions/Function0;)V", "app_debug"})
public final class DemoSwitchersKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DemoRoleSwitcher(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.UserRole currentRole, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.gigconnect.app.data.model.UserRole, kotlin.Unit> onRoleSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DemoLocationSwitcher(@org.jetbrains.annotations.NotNull()
    java.util.List<com.gigconnect.app.data.model.DemoCity> cities, @org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.DemoCity selectedCity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.gigconnect.app.data.model.DemoCity, kotlin.Unit> onCitySelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}
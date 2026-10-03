package com.gigconnect.app.ui.onboarding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a<\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a8\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a0\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0003\u00a8\u0006\u0015"}, d2 = {"AuthScreen", "", "appViewModel", "Lcom/gigconnect/app/viewmodel/AppViewModel;", "initialIsRegister", "", "onAuthSuccess", "Lkotlin/Function1;", "Lcom/gigconnect/app/data/model/UserRole;", "onBack", "Lkotlin/Function0;", "RoleChip", "label", "", "icon", "selected", "modifier", "Landroidx/compose/ui/Modifier;", "onClick", "TabButton", "text", "app_debug"})
public final class AuthScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AuthScreen(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.viewmodel.AppViewModel appViewModel, boolean initialIsRegister, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.gigconnect.app.data.model.UserRole, kotlin.Unit> onAuthSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TabButton(java.lang.String text, boolean selected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RoleChip(java.lang.String label, java.lang.String icon, boolean selected, androidx.compose.ui.Modifier modifier, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}
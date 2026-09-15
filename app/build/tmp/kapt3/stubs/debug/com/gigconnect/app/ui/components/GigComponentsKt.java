package com.gigconnect.app.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001aL\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a \u0010\f\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001aL\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\u001e\b\u0002\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011\u00a2\u0006\u0002\b\u0013\u00a2\u0006\u0002\b\u0014H\u0007\u001a\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0003\u001a\u0012\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001a\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a6\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001fH\u0003\u001aL\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020&2\b\b\u0002\u0010\'\u001a\u00020&2\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+\u001a.\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020$2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\u001fH\u0007\u001a(\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u0002032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001c\u00105\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u00106\u001a\u000207H\u0007\u001a\u001a\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020:2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001a\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a&\u0010;\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010<\u001a\u00020\u001f2\b\b\u0002\u0010=\u001a\u00020\u001fH\u0007\u001a(\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020@2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\b\u0010A\u001a\u00020\u0001H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006B"}, d2 = {"DemoDataBadge", "", "modifier", "Landroidx/compose/ui/Modifier;", "EmptyState", "icon", "", "title", "subtitle", "actionLabel", "onAction", "Lkotlin/Function0;", "ErrorState", "onRetry", "GigTopBar", "onBack", "actions", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "InfoPill", "text", "OfflineBanner", "PriceBreakdownCard", "pricing", "Lcom/gigconnect/app/data/model/PricingBreakdown;", "PriceLine", "label", "value", "isSubtle", "", "highlight", "isBold", "ProgressRing", "progress", "", "size", "Landroidx/compose/ui/unit/Dp;", "strokeWidth", "color", "Landroidx/compose/ui/graphics/Color;", "ProgressRing-lN-94pE", "(FFFJLjava/lang/String;Landroidx/compose/ui/Modifier;)V", "RatingBar", "rating", "reviewCount", "", "showCount", "ServiceCategoryCard", "service", "Lcom/gigconnect/app/data/model/ServiceCategory;", "onClick", "SkeletonBox", "shape", "Landroidx/compose/ui/graphics/Shape;", "StatusChip", "status", "Lcom/gigconnect/app/data/model/BookingStatus;", "VerifiedBadge", "small", "isVerified", "WorkerCard", "worker", "Lcom/gigconnect/app/data/model/Worker;", "WorkerCardSkeleton", "app_debug"})
public final class GigComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DemoDataBadge(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void VerifiedBadge(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, boolean small, boolean isVerified) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RatingBar(float rating, int reviewCount, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, boolean showCount) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WorkerCard(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.Worker worker, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void InfoPill(java.lang.String icon, java.lang.String text) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ServiceCategoryCard(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.ServiceCategory service, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PriceBreakdownCard(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.PricingBreakdown pricing, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PriceLine(java.lang.String label, java.lang.String value, boolean isSubtle, boolean highlight, boolean isBold) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SkeletonBox(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.Shape shape) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void WorkerCardSkeleton() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState(@org.jetbrains.annotations.NotNull()
    java.lang.String icon, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.Nullable()
    java.lang.String actionLabel, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAction, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ErrorState(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void OfflineBanner(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void GigTopBar(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.RowScope, kotlin.Unit> actions) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatusChip(@org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatusChip(@org.jetbrains.annotations.NotNull()
    com.gigconnect.app.data.model.BookingStatus status, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}
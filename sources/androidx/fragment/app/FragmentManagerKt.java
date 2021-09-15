package androidx.fragment.app;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\tH\b¨\u0006\n"}, d2 = {"transaction", "", "Landroidx/fragment/app/FragmentManager;", "now", "", "allowStateLoss", "body", "Lkotlin/Function1;", "Landroidx/fragment/app/FragmentTransaction;", "Lkotlin/ExtensionFunctionType;", "fragment-ktx_release"}, k = 2, mv = {1, 1, 10})
/* compiled from: FragmentManager.kt */
public final class FragmentManagerKt {
    public static /* bridge */ /* synthetic */ void transaction$default(FragmentManager $receiver, boolean now, boolean allowStateLoss, Function1 body, int i, Object obj) {
        if ((i & 1) != 0) {
            now = false;
        }
        if ((i & 2) != 0) {
            allowStateLoss = false;
        }
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(body, "body");
        FragmentTransaction transaction = $receiver.beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(transaction, "transaction");
        body.invoke(transaction);
        if (now) {
            if (allowStateLoss) {
                transaction.commitNowAllowingStateLoss();
            } else {
                transaction.commitNow();
            }
        } else if (allowStateLoss) {
            transaction.commitAllowingStateLoss();
        } else {
            transaction.commit();
        }
    }

    public static final void transaction(@NotNull FragmentManager $receiver, boolean now, boolean allowStateLoss, @NotNull Function1<? super FragmentTransaction, Unit> body) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(body, "body");
        FragmentTransaction transaction = $receiver.beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(transaction, "transaction");
        body.invoke(transaction);
        if (now) {
            if (allowStateLoss) {
                transaction.commitNowAllowingStateLoss();
            } else {
                transaction.commitNow();
            }
        } else if (allowStateLoss) {
            transaction.commitAllowingStateLoss();
        } else {
            transaction.commit();
        }
    }
}

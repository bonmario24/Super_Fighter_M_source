package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzby;
import com.google.android.gms.internal.firebase_auth.zzca;
import com.google.android.gms.internal.firebase_auth.zzcc;
import com.google.android.gms.internal.firebase_auth.zzce;
import com.google.android.gms.internal.firebase_auth.zzcg;
import com.google.android.gms.internal.firebase_auth.zzci;
import com.google.android.gms.internal.firebase_auth.zzck;
import com.google.android.gms.internal.firebase_auth.zzcm;
import com.google.android.gms.internal.firebase_auth.zzco;
import com.google.android.gms.internal.firebase_auth.zzcq;
import com.google.android.gms.internal.firebase_auth.zzcs;
import com.google.android.gms.internal.firebase_auth.zzcu;
import com.google.android.gms.internal.firebase_auth.zzcw;
import com.google.android.gms.internal.firebase_auth.zzcy;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzda;
import com.google.android.gms.internal.firebase_auth.zzdc;
import com.google.android.gms.internal.firebase_auth.zzde;
import com.google.android.gms.internal.firebase_auth.zzdg;
import com.google.android.gms.internal.firebase_auth.zzdi;
import com.google.android.gms.internal.firebase_auth.zzdk;
import com.google.android.gms.internal.firebase_auth.zzdm;
import com.google.android.gms.internal.firebase_auth.zzdo;
import com.google.android.gms.internal.firebase_auth.zzdq;
import com.google.android.gms.internal.firebase_auth.zzds;
import com.google.android.gms.internal.firebase_auth.zzdu;
import com.google.android.gms.internal.firebase_auth.zzdw;
import com.google.android.gms.internal.firebase_auth.zzdy;
import com.google.android.gms.internal.firebase_auth.zzea;
import com.google.android.gms.internal.firebase_auth.zzec;
import com.google.android.gms.internal.firebase_auth.zzee;
import com.google.android.gms.internal.firebase_auth.zzeg;
import com.google.android.gms.internal.firebase_auth.zzei;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzgc;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
public abstract class zzeq extends zza implements zzer {
    public zzeq() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzem zzeo;
        zzem zzem = null;
        switch (i) {
            case 1:
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzeo = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface instanceof zzem) {
                        zzeo = (zzem) queryLocalInterface;
                    } else {
                        zzeo = new zzeo(readStrongBinder);
                    }
                }
                zza(readString, zzeo);
                break;
            case 2:
                String readString2 = parcel.readString();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface2 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface2;
                    } else {
                        zzem = new zzeo(readStrongBinder2);
                    }
                }
                zzb(readString2, zzem);
                break;
            case 3:
                zzgc zzgc = (zzgc) zzd.zza(parcel, zzgc.CREATOR);
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface3 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface3;
                    } else {
                        zzem = new zzeo(readStrongBinder3);
                    }
                }
                zza(zzgc, zzem);
                break;
            case 4:
                String readString3 = parcel.readString();
                UserProfileChangeRequest userProfileChangeRequest = (UserProfileChangeRequest) zzd.zza(parcel, UserProfileChangeRequest.CREATOR);
                IBinder readStrongBinder4 = parcel.readStrongBinder();
                if (readStrongBinder4 != null) {
                    IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface4 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface4;
                    } else {
                        zzem = new zzeo(readStrongBinder4);
                    }
                }
                zza(readString3, userProfileChangeRequest, zzem);
                break;
            case 5:
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                IBinder readStrongBinder5 = parcel.readStrongBinder();
                if (readStrongBinder5 != null) {
                    IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface5 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface5;
                    } else {
                        zzem = new zzeo(readStrongBinder5);
                    }
                }
                zza(readString4, readString5, zzem);
                break;
            case 6:
                String readString6 = parcel.readString();
                String readString7 = parcel.readString();
                IBinder readStrongBinder6 = parcel.readStrongBinder();
                if (readStrongBinder6 != null) {
                    IInterface queryLocalInterface6 = readStrongBinder6.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface6 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface6;
                    } else {
                        zzem = new zzeo(readStrongBinder6);
                    }
                }
                zzb(readString6, readString7, zzem);
                break;
            case 7:
                String readString8 = parcel.readString();
                String readString9 = parcel.readString();
                IBinder readStrongBinder7 = parcel.readStrongBinder();
                if (readStrongBinder7 != null) {
                    IInterface queryLocalInterface7 = readStrongBinder7.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface7 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface7;
                    } else {
                        zzem = new zzeo(readStrongBinder7);
                    }
                }
                zzc(readString8, readString9, zzem);
                break;
            case 8:
                String readString10 = parcel.readString();
                String readString11 = parcel.readString();
                IBinder readStrongBinder8 = parcel.readStrongBinder();
                if (readStrongBinder8 != null) {
                    IInterface queryLocalInterface8 = readStrongBinder8.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface8 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface8;
                    } else {
                        zzem = new zzeo(readStrongBinder8);
                    }
                }
                zzd(readString10, readString11, zzem);
                break;
            case 9:
                String readString12 = parcel.readString();
                IBinder readStrongBinder9 = parcel.readStrongBinder();
                if (readStrongBinder9 != null) {
                    IInterface queryLocalInterface9 = readStrongBinder9.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface9 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface9;
                    } else {
                        zzem = new zzeo(readStrongBinder9);
                    }
                }
                zzc(readString12, zzem);
                break;
            case 10:
                String readString13 = parcel.readString();
                IBinder readStrongBinder10 = parcel.readStrongBinder();
                if (readStrongBinder10 != null) {
                    IInterface queryLocalInterface10 = readStrongBinder10.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface10 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface10;
                    } else {
                        zzem = new zzeo(readStrongBinder10);
                    }
                }
                zzd(readString13, zzem);
                break;
            case 11:
                String readString14 = parcel.readString();
                String readString15 = parcel.readString();
                String readString16 = parcel.readString();
                IBinder readStrongBinder11 = parcel.readStrongBinder();
                if (readStrongBinder11 != null) {
                    IInterface queryLocalInterface11 = readStrongBinder11.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface11 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface11;
                    } else {
                        zzem = new zzeo(readStrongBinder11);
                    }
                }
                zza(readString14, readString15, readString16, zzem);
                break;
            case 12:
                String readString17 = parcel.readString();
                zzgc zzgc2 = (zzgc) zzd.zza(parcel, zzgc.CREATOR);
                IBinder readStrongBinder12 = parcel.readStrongBinder();
                if (readStrongBinder12 != null) {
                    IInterface queryLocalInterface12 = readStrongBinder12.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface12 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface12;
                    } else {
                        zzem = new zzeo(readStrongBinder12);
                    }
                }
                zza(readString17, zzgc2, zzem);
                break;
            case 13:
                String readString18 = parcel.readString();
                IBinder readStrongBinder13 = parcel.readStrongBinder();
                if (readStrongBinder13 != null) {
                    IInterface queryLocalInterface13 = readStrongBinder13.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface13 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface13;
                    } else {
                        zzem = new zzeo(readStrongBinder13);
                    }
                }
                zze(readString18, zzem);
                break;
            case 14:
                String readString19 = parcel.readString();
                String readString20 = parcel.readString();
                IBinder readStrongBinder14 = parcel.readStrongBinder();
                if (readStrongBinder14 != null) {
                    IInterface queryLocalInterface14 = readStrongBinder14.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface14 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface14;
                    } else {
                        zzem = new zzeo(readStrongBinder14);
                    }
                }
                zze(readString19, readString20, zzem);
                break;
            case 15:
                String readString21 = parcel.readString();
                IBinder readStrongBinder15 = parcel.readStrongBinder();
                if (readStrongBinder15 != null) {
                    IInterface queryLocalInterface15 = readStrongBinder15.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface15 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface15;
                    } else {
                        zzem = new zzeo(readStrongBinder15);
                    }
                }
                zzf(readString21, zzem);
                break;
            case 16:
                IBinder readStrongBinder16 = parcel.readStrongBinder();
                if (readStrongBinder16 != null) {
                    IInterface queryLocalInterface16 = readStrongBinder16.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface16 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface16;
                    } else {
                        zzem = new zzeo(readStrongBinder16);
                    }
                }
                zza(zzem);
                break;
            case 17:
                String readString22 = parcel.readString();
                IBinder readStrongBinder17 = parcel.readStrongBinder();
                if (readStrongBinder17 != null) {
                    IInterface queryLocalInterface17 = readStrongBinder17.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface17 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface17;
                    } else {
                        zzem = new zzeo(readStrongBinder17);
                    }
                }
                zzg(readString22, zzem);
                break;
            case 18:
                String readString23 = parcel.readString();
                IBinder readStrongBinder18 = parcel.readStrongBinder();
                if (readStrongBinder18 != null) {
                    IInterface queryLocalInterface18 = readStrongBinder18.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface18 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface18;
                    } else {
                        zzem = new zzeo(readStrongBinder18);
                    }
                }
                zzh(readString23, zzem);
                break;
            case 19:
                String readString24 = parcel.readString();
                IBinder readStrongBinder19 = parcel.readStrongBinder();
                if (readStrongBinder19 != null) {
                    IInterface queryLocalInterface19 = readStrongBinder19.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface19 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface19;
                    } else {
                        zzem = new zzeo(readStrongBinder19);
                    }
                }
                zzi(readString24, zzem);
                break;
            case 20:
                String readString25 = parcel.readString();
                IBinder readStrongBinder20 = parcel.readStrongBinder();
                if (readStrongBinder20 != null) {
                    IInterface queryLocalInterface20 = readStrongBinder20.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface20 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface20;
                    } else {
                        zzem = new zzeo(readStrongBinder20);
                    }
                }
                zzj(readString25, zzem);
                break;
            case 21:
                String readString26 = parcel.readString();
                String readString27 = parcel.readString();
                IBinder readStrongBinder21 = parcel.readStrongBinder();
                if (readStrongBinder21 != null) {
                    IInterface queryLocalInterface21 = readStrongBinder21.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface21 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface21;
                    } else {
                        zzem = new zzeo(readStrongBinder21);
                    }
                }
                zzf(readString26, readString27, zzem);
                break;
            case 22:
                zzfr zzfr = (zzfr) zzd.zza(parcel, zzfr.CREATOR);
                IBinder readStrongBinder22 = parcel.readStrongBinder();
                if (readStrongBinder22 != null) {
                    IInterface queryLocalInterface22 = readStrongBinder22.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface22 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface22;
                    } else {
                        zzem = new zzeo(readStrongBinder22);
                    }
                }
                zza(zzfr, zzem);
                break;
            case 23:
                PhoneAuthCredential phoneAuthCredential = (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder readStrongBinder23 = parcel.readStrongBinder();
                if (readStrongBinder23 != null) {
                    IInterface queryLocalInterface23 = readStrongBinder23.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface23 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface23;
                    } else {
                        zzem = new zzeo(readStrongBinder23);
                    }
                }
                zza(phoneAuthCredential, zzem);
                break;
            case 24:
                String readString28 = parcel.readString();
                PhoneAuthCredential phoneAuthCredential2 = (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder readStrongBinder24 = parcel.readStrongBinder();
                if (readStrongBinder24 != null) {
                    IInterface queryLocalInterface24 = readStrongBinder24.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface24 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface24;
                    } else {
                        zzem = new zzeo(readStrongBinder24);
                    }
                }
                zza(readString28, phoneAuthCredential2, zzem);
                break;
            case 25:
                String readString29 = parcel.readString();
                ActionCodeSettings actionCodeSettings = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder25 = parcel.readStrongBinder();
                if (readStrongBinder25 != null) {
                    IInterface queryLocalInterface25 = readStrongBinder25.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface25 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface25;
                    } else {
                        zzem = new zzeo(readStrongBinder25);
                    }
                }
                zza(readString29, actionCodeSettings, zzem);
                break;
            case 26:
                String readString30 = parcel.readString();
                ActionCodeSettings actionCodeSettings2 = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder26 = parcel.readStrongBinder();
                if (readStrongBinder26 != null) {
                    IInterface queryLocalInterface26 = readStrongBinder26.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface26 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface26;
                    } else {
                        zzem = new zzeo(readStrongBinder26);
                    }
                }
                zzb(readString30, actionCodeSettings2, zzem);
                break;
            case 27:
                String readString31 = parcel.readString();
                IBinder readStrongBinder27 = parcel.readStrongBinder();
                if (readStrongBinder27 != null) {
                    IInterface queryLocalInterface27 = readStrongBinder27.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface27 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface27;
                    } else {
                        zzem = new zzeo(readStrongBinder27);
                    }
                }
                zzk(readString31, zzem);
                break;
            case 28:
                String readString32 = parcel.readString();
                ActionCodeSettings actionCodeSettings3 = (ActionCodeSettings) zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder readStrongBinder28 = parcel.readStrongBinder();
                if (readStrongBinder28 != null) {
                    IInterface queryLocalInterface28 = readStrongBinder28.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface28 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface28;
                    } else {
                        zzem = new zzeo(readStrongBinder28);
                    }
                }
                zzc(readString32, actionCodeSettings3, zzem);
                break;
            case 29:
                EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zzd.zza(parcel, EmailAuthCredential.CREATOR);
                IBinder readStrongBinder29 = parcel.readStrongBinder();
                if (readStrongBinder29 != null) {
                    IInterface queryLocalInterface29 = readStrongBinder29.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface29 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface29;
                    } else {
                        zzem = new zzeo(readStrongBinder29);
                    }
                }
                zza(emailAuthCredential, zzem);
                break;
            case 101:
                zzcq zzcq = (zzcq) zzd.zza(parcel, zzcq.CREATOR);
                IBinder readStrongBinder30 = parcel.readStrongBinder();
                if (readStrongBinder30 != null) {
                    IInterface queryLocalInterface30 = readStrongBinder30.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface30 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface30;
                    } else {
                        zzem = new zzeo(readStrongBinder30);
                    }
                }
                zza(zzcq, zzem);
                break;
            case 102:
                zzdo zzdo = (zzdo) zzd.zza(parcel, zzdo.CREATOR);
                IBinder readStrongBinder31 = parcel.readStrongBinder();
                if (readStrongBinder31 != null) {
                    IInterface queryLocalInterface31 = readStrongBinder31.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface31 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface31;
                    } else {
                        zzem = new zzeo(readStrongBinder31);
                    }
                }
                zza(zzdo, zzem);
                break;
            case 103:
                zzdm zzdm = (zzdm) zzd.zza(parcel, zzdm.CREATOR);
                IBinder readStrongBinder32 = parcel.readStrongBinder();
                if (readStrongBinder32 != null) {
                    IInterface queryLocalInterface32 = readStrongBinder32.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface32 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface32;
                    } else {
                        zzem = new zzeo(readStrongBinder32);
                    }
                }
                zza(zzdm, zzem);
                break;
            case 104:
                zzeg zzeg = (zzeg) zzd.zza(parcel, zzeg.CREATOR);
                IBinder readStrongBinder33 = parcel.readStrongBinder();
                if (readStrongBinder33 != null) {
                    IInterface queryLocalInterface33 = readStrongBinder33.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface33 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface33;
                    } else {
                        zzem = new zzeo(readStrongBinder33);
                    }
                }
                zza(zzeg, zzem);
                break;
            case 105:
                zzca zzca = (zzca) zzd.zza(parcel, zzca.CREATOR);
                IBinder readStrongBinder34 = parcel.readStrongBinder();
                if (readStrongBinder34 != null) {
                    IInterface queryLocalInterface34 = readStrongBinder34.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface34 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface34;
                    } else {
                        zzem = new zzeo(readStrongBinder34);
                    }
                }
                zza(zzca, zzem);
                break;
            case 106:
                zzcc zzcc = (zzcc) zzd.zza(parcel, zzcc.CREATOR);
                IBinder readStrongBinder35 = parcel.readStrongBinder();
                if (readStrongBinder35 != null) {
                    IInterface queryLocalInterface35 = readStrongBinder35.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface35 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface35;
                    } else {
                        zzem = new zzeo(readStrongBinder35);
                    }
                }
                zza(zzcc, zzem);
                break;
            case 107:
                zzci zzci = (zzci) zzd.zza(parcel, zzci.CREATOR);
                IBinder readStrongBinder36 = parcel.readStrongBinder();
                if (readStrongBinder36 != null) {
                    IInterface queryLocalInterface36 = readStrongBinder36.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface36 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface36;
                    } else {
                        zzem = new zzeo(readStrongBinder36);
                    }
                }
                zza(zzci, zzem);
                break;
            case 108:
                zzdq zzdq = (zzdq) zzd.zza(parcel, zzdq.CREATOR);
                IBinder readStrongBinder37 = parcel.readStrongBinder();
                if (readStrongBinder37 != null) {
                    IInterface queryLocalInterface37 = readStrongBinder37.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface37 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface37;
                    } else {
                        zzem = new zzeo(readStrongBinder37);
                    }
                }
                zza(zzdq, zzem);
                break;
            case 109:
                zzcs zzcs = (zzcs) zzd.zza(parcel, zzcs.CREATOR);
                IBinder readStrongBinder38 = parcel.readStrongBinder();
                if (readStrongBinder38 != null) {
                    IInterface queryLocalInterface38 = readStrongBinder38.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface38 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface38;
                    } else {
                        zzem = new zzeo(readStrongBinder38);
                    }
                }
                zza(zzcs, zzem);
                break;
            case 111:
                zzcu zzcu = (zzcu) zzd.zza(parcel, zzcu.CREATOR);
                IBinder readStrongBinder39 = parcel.readStrongBinder();
                if (readStrongBinder39 != null) {
                    IInterface queryLocalInterface39 = readStrongBinder39.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface39 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface39;
                    } else {
                        zzem = new zzeo(readStrongBinder39);
                    }
                }
                zza(zzcu, zzem);
                break;
            case 112:
                zzcw zzcw = (zzcw) zzd.zza(parcel, zzcw.CREATOR);
                IBinder readStrongBinder40 = parcel.readStrongBinder();
                if (readStrongBinder40 != null) {
                    IInterface queryLocalInterface40 = readStrongBinder40.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface40 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface40;
                    } else {
                        zzem = new zzeo(readStrongBinder40);
                    }
                }
                zza(zzcw, zzem);
                break;
            case 113:
                zzec zzec = (zzec) zzd.zza(parcel, zzec.CREATOR);
                IBinder readStrongBinder41 = parcel.readStrongBinder();
                if (readStrongBinder41 != null) {
                    IInterface queryLocalInterface41 = readStrongBinder41.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface41 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface41;
                    } else {
                        zzem = new zzeo(readStrongBinder41);
                    }
                }
                zza(zzec, zzem);
                break;
            case 114:
                zzee zzee = (zzee) zzd.zza(parcel, zzee.CREATOR);
                IBinder readStrongBinder42 = parcel.readStrongBinder();
                if (readStrongBinder42 != null) {
                    IInterface queryLocalInterface42 = readStrongBinder42.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface42 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface42;
                    } else {
                        zzem = new zzeo(readStrongBinder42);
                    }
                }
                zza(zzee, zzem);
                break;
            case 115:
                zzda zzda = (zzda) zzd.zza(parcel, zzda.CREATOR);
                IBinder readStrongBinder43 = parcel.readStrongBinder();
                if (readStrongBinder43 != null) {
                    IInterface queryLocalInterface43 = readStrongBinder43.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface43 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface43;
                    } else {
                        zzem = new zzeo(readStrongBinder43);
                    }
                }
                zza(zzda, zzem);
                break;
            case 116:
                zzdk zzdk = (zzdk) zzd.zza(parcel, zzdk.CREATOR);
                IBinder readStrongBinder44 = parcel.readStrongBinder();
                if (readStrongBinder44 != null) {
                    IInterface queryLocalInterface44 = readStrongBinder44.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface44 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface44;
                    } else {
                        zzem = new zzeo(readStrongBinder44);
                    }
                }
                zza(zzdk, zzem);
                break;
            case 117:
                zzck zzck = (zzck) zzd.zza(parcel, zzck.CREATOR);
                IBinder readStrongBinder45 = parcel.readStrongBinder();
                if (readStrongBinder45 != null) {
                    IInterface queryLocalInterface45 = readStrongBinder45.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface45 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface45;
                    } else {
                        zzem = new zzeo(readStrongBinder45);
                    }
                }
                zza(zzck, zzem);
                break;
            case 119:
                zzce zzce = (zzce) zzd.zza(parcel, zzce.CREATOR);
                IBinder readStrongBinder46 = parcel.readStrongBinder();
                if (readStrongBinder46 != null) {
                    IInterface queryLocalInterface46 = readStrongBinder46.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface46 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface46;
                    } else {
                        zzem = new zzeo(readStrongBinder46);
                    }
                }
                zza(zzce, zzem);
                break;
            case 120:
                zzby zzby = (zzby) zzd.zza(parcel, zzby.CREATOR);
                IBinder readStrongBinder47 = parcel.readStrongBinder();
                if (readStrongBinder47 != null) {
                    IInterface queryLocalInterface47 = readStrongBinder47.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface47 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface47;
                    } else {
                        zzem = new zzeo(readStrongBinder47);
                    }
                }
                zza(zzby, zzem);
                break;
            case 121:
                zzcg zzcg = (zzcg) zzd.zza(parcel, zzcg.CREATOR);
                IBinder readStrongBinder48 = parcel.readStrongBinder();
                if (readStrongBinder48 != null) {
                    IInterface queryLocalInterface48 = readStrongBinder48.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface48 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface48;
                    } else {
                        zzem = new zzeo(readStrongBinder48);
                    }
                }
                zza(zzcg, zzem);
                break;
            case 122:
                zzdg zzdg = (zzdg) zzd.zza(parcel, zzdg.CREATOR);
                IBinder readStrongBinder49 = parcel.readStrongBinder();
                if (readStrongBinder49 != null) {
                    IInterface queryLocalInterface49 = readStrongBinder49.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface49 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface49;
                    } else {
                        zzem = new zzeo(readStrongBinder49);
                    }
                }
                zza(zzdg, zzem);
                break;
            case 123:
                zzdu zzdu = (zzdu) zzd.zza(parcel, zzdu.CREATOR);
                IBinder readStrongBinder50 = parcel.readStrongBinder();
                if (readStrongBinder50 != null) {
                    IInterface queryLocalInterface50 = readStrongBinder50.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface50 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface50;
                    } else {
                        zzem = new zzeo(readStrongBinder50);
                    }
                }
                zza(zzdu, zzem);
                break;
            case 124:
                zzcy zzcy = (zzcy) zzd.zza(parcel, zzcy.CREATOR);
                IBinder readStrongBinder51 = parcel.readStrongBinder();
                if (readStrongBinder51 != null) {
                    IInterface queryLocalInterface51 = readStrongBinder51.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface51 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface51;
                    } else {
                        zzem = new zzeo(readStrongBinder51);
                    }
                }
                zza(zzcy, zzem);
                break;
            case 126:
                zzdc zzdc = (zzdc) zzd.zza(parcel, zzdc.CREATOR);
                IBinder readStrongBinder52 = parcel.readStrongBinder();
                if (readStrongBinder52 != null) {
                    IInterface queryLocalInterface52 = readStrongBinder52.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface52 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface52;
                    } else {
                        zzem = new zzeo(readStrongBinder52);
                    }
                }
                zza(zzdc, zzem);
                break;
            case 127:
                zzdi zzdi = (zzdi) zzd.zza(parcel, zzdi.CREATOR);
                IBinder readStrongBinder53 = parcel.readStrongBinder();
                if (readStrongBinder53 != null) {
                    IInterface queryLocalInterface53 = readStrongBinder53.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface53 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface53;
                    } else {
                        zzem = new zzeo(readStrongBinder53);
                    }
                }
                zza(zzdi, zzem);
                break;
            case 128:
                zzde zzde = (zzde) zzd.zza(parcel, zzde.CREATOR);
                IBinder readStrongBinder54 = parcel.readStrongBinder();
                if (readStrongBinder54 != null) {
                    IInterface queryLocalInterface54 = readStrongBinder54.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface54 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface54;
                    } else {
                        zzem = new zzeo(readStrongBinder54);
                    }
                }
                zza(zzde, zzem);
                break;
            case 129:
                zzds zzds = (zzds) zzd.zza(parcel, zzds.CREATOR);
                IBinder readStrongBinder55 = parcel.readStrongBinder();
                if (readStrongBinder55 != null) {
                    IInterface queryLocalInterface55 = readStrongBinder55.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface55 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface55;
                    } else {
                        zzem = new zzeo(readStrongBinder55);
                    }
                }
                zza(zzds, zzem);
                break;
            case 130:
                zzdw zzdw = (zzdw) zzd.zza(parcel, zzdw.CREATOR);
                IBinder readStrongBinder56 = parcel.readStrongBinder();
                if (readStrongBinder56 != null) {
                    IInterface queryLocalInterface56 = readStrongBinder56.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface56 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface56;
                    } else {
                        zzem = new zzeo(readStrongBinder56);
                    }
                }
                zza(zzdw, zzem);
                break;
            case 131:
                zzea zzea = (zzea) zzd.zza(parcel, zzea.CREATOR);
                IBinder readStrongBinder57 = parcel.readStrongBinder();
                if (readStrongBinder57 != null) {
                    IInterface queryLocalInterface57 = readStrongBinder57.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface57 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface57;
                    } else {
                        zzem = new zzeo(readStrongBinder57);
                    }
                }
                zza(zzea, zzem);
                break;
            case 132:
                zzcm zzcm = (zzcm) zzd.zza(parcel, zzcm.CREATOR);
                IBinder readStrongBinder58 = parcel.readStrongBinder();
                if (readStrongBinder58 != null) {
                    IInterface queryLocalInterface58 = readStrongBinder58.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface58 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface58;
                    } else {
                        zzem = new zzeo(readStrongBinder58);
                    }
                }
                zza(zzcm, zzem);
                break;
            case 133:
                zzdy zzdy = (zzdy) zzd.zza(parcel, zzdy.CREATOR);
                IBinder readStrongBinder59 = parcel.readStrongBinder();
                if (readStrongBinder59 != null) {
                    IInterface queryLocalInterface59 = readStrongBinder59.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface59 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface59;
                    } else {
                        zzem = new zzeo(readStrongBinder59);
                    }
                }
                zza(zzdy, zzem);
                break;
            case 134:
                zzco zzco = (zzco) zzd.zza(parcel, zzco.CREATOR);
                IBinder readStrongBinder60 = parcel.readStrongBinder();
                if (readStrongBinder60 != null) {
                    IInterface queryLocalInterface60 = readStrongBinder60.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (queryLocalInterface60 instanceof zzem) {
                        zzem = (zzem) queryLocalInterface60;
                    } else {
                        zzem = new zzeo(readStrongBinder60);
                    }
                }
                zza(zzco, zzem);
                break;
            case 135:
                zzei zzei = (zzei) zzd.zza(parcel, zzei.CREATOR);
                IBinder readStrongBinder61 = parcel.readStrongBinder();
                if (readStrongBinder61 != null) {
                    IInterface queryLocalInterface61 = readStrongBinder61.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    zzem = queryLocalInterface61 instanceof zzem ? (zzem) queryLocalInterface61 : new zzeo(readStrongBinder61);
                }
                zza(zzei, zzem);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}

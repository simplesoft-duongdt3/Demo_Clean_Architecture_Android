package com.duongdt3.data.dataservice.network.converter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.duongdt3.data.dataservice.network.model.WrappedResponse;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class WrappedResponseConverterFactory extends Converter.Factory {

    private GsonConverterFactory gsonConverterFactory;
    @NonNull
    private final List<Class<?>> excludeClasses;

    public WrappedResponseConverterFactory(GsonConverterFactory gsonConverterFactory, @NotNull List<Class<?>> excludeClasses) {
        this.gsonConverterFactory = gsonConverterFactory;
        this.excludeClasses = excludeClasses;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type,
                                                            Annotation[] annotations, Retrofit retrofit) {
        if (isExcludeType(type)) {
            return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit);
        } else {
            Type wrappedType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }

                @NotNull
                @Override
                public Type getRawType() {
                    return WrappedResponse.class;
                }
            };
            Converter<ResponseBody, ?> gsonConverter = gsonConverterFactory
                    .responseBodyConverter(wrappedType, annotations, retrofit);
            return new WrappedResponseBodyConverter(gsonConverter);
        }
    }

    
    private boolean isExcludeType(Type type) {
        boolean isExclude = false;
        if (type instanceof Class<?>) {
            Class<?> clazzType = (Class<?>) type;
            for (Class<?> excludeClass : excludeClasses) {
                isExclude = excludeClass.equals(clazzType);
                if (isExclude) {
                    break;
                }
            }
        }
        return isExclude;
    }
}
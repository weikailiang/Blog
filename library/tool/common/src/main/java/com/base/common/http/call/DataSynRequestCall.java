package com.base.common.http.call;

import retrofit2.Call;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class DataSynRequestCall extends BaseSynRequestCall{
    public DataSynRequestCall(Call<String> call) {
        super(call);
    }
}

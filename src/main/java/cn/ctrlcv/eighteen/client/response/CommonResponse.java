package cn.ctrlcv.eighteen.client.response;

import lombok.Data;

/**
 * Class Name: CommonResponse
 * Class Description: 通用返回对象
 *
 * @author liujm
 * @date 2023-05-02
 */
@Data
public class CommonResponse {

    private String errcode;
    private String errmsg;
    private String msgid = "";
}

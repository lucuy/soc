package com.util;

import java.util.UUID;

/**
 * 随机获得Guid公共类
 * @author wangyanan
 *
 */
public class CreateGuid
{
    public static final String GenerateGUID()
    {
        UUID uui = UUID.randomUUID();
        return uui.toString();
    }
}

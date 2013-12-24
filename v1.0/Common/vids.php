<?php
function getvideoid($url)
{
    $data['status'] = 0;
    if (strpos($url, 'youku.com')) {
        $data['type'] = 'youku';
        if (strpos($url, 'html')) {
            $data['id'] = inter($url, 'id_', '.html');
        } elseif (strpos($url, 'swf')) {
            $data['id'] = inter($url, '/sid/', '/');
        } else {
            urldebug($url);
        }
    } elseif (strpos($url, 'tudou.com') || strpos($url, 'tudouui.com')) {
        $data['type'] = 'tudou';
        $data['id'] = '';
        if (strpos($url, 'swf')) {
            $wd = inter($url, 'iid=', '/');
            if (strpos($wd, 'swf')) {
                $wd = inter($url, 'iid=', '&');
            }
            $data['id'] = $wd;
        }
        if (!$data['id']) {
            $content = get_curl_contents($url);
            $wd = inter($content, 'vcode:"', '"');
            if (!$wd) {
                $wd = inter($content, 'vcode: \'', '\'');
            }
            if ($wd) {
                $data['type'] = 'youku';
                $data['id'] = $wd;
            } else {
                $data['id'] = trim(inter($content, 'iid:', ','));
            }
        }
        if (!$data['id']) {
            urldebug($url);
        }
    } elseif (strpos($url, 'letv.com')) {
        $data['type'] = 'letv';
        if (strpos($url, 'swf')) {
            $wd = inter($url, 'swf?id=', '&');
            $data['id'] = $wd;
        } else {
            $content = get_curl_contents($url);
            $wd = inter($content, 'vid:', ',');
            if ($wd) {
                $data['id'] = $wd;
            } elseif ($wd == 0) {
                $data['id'] = inter($content, 'vid=', '&');
            } else {
                urldebug($url);
            }
        }
    } elseif (strpos($url, '56.com')) {
        $data['type'] = '56';
        if (strpos($url, 'v_')) {
            $wd = inter($url, 'v_', '.');
        } elseif (strpos($url, 'cpm_')) {
            $wd = inter($url, 'cpm_', '.');
        } elseif (strpos($url, 'vid-')) {
            $wd = inter($url, 'vid-', '.');
        } elseif (strpos($url, 'open_')) {
            $wd = inter($url, 'open_', '.');
        } elseif (strpos($url, 'redian/')) {
            $wd = explode('redian/', $url);
            $wd2 = explode('/', $wd[1]);
            $wd = '';
            $wd = $wd2[0];
            if ($wd2[1]) {
                $wd = $wd2[1];
            }
        }
        if ($wd) {
            $data['id'] = $wd;
        } else {
            urldebug($url);
        }
    } elseif (strpos($url, 'pan.baidu') || strpos($url, 'yun.baidu')) {
        $data['type'] = 'baidu';
        if (strpos($url, 'shareid=')) {
            list($add, $wd) = explode('link?', $url);
            strpos($url, '&shareid=') && list($uk, $id) = explode('&shareid=', $wd);
            strpos($url, '&uk=') && list($id, $uk) = explode('&uk=', $wd);
            $id = strtr($id, array("shareid=" => ""));
            $uk = strtr($uk, array("uk=" => ""));
            $wd = $id . '-' . $uk;
        } else {
            list($add, $wd) = explode('/s/', $url);
        }
        if ($wd) {
            $data['id'] = $wd;
        } else {
            urldebug($url);
        }
    } elseif (strpos($url, 'ku6.com')) {
        $data['type'] = 'ku6';
        if (strpos($url, 'html')) {
            $arr = explode('/', $url);
            $wd = $arr[count($arr) - 1];
            $wd = str_replace('.html', '', $wd);
        } elseif (strpos($url, 'swf')) {
            $arr = explode('/', $url);
            $wd = $arr[count($arr) - 2];
        } else {
            urldebug($url);
        }
        if ($wd) {
            $data['id'] = $wd;
        } else {
            urldebug($url);
        }
    } else if (strpos($url, "pps.tv")) {
        $data['type'] = 'pps';
        $arr = explode('/', $url);
        $wd = $arr[count($arr) - 1];
        $wd = str_replace('.html', '', $wd);
        if ($wd) {
            $arr_id = explode('_', $wd);
            $data['id'] = $arr_id[1];
        } else {
            urldebug($url);
        }
        //echo $data['id'] ;
    } else {
        $data['type'] = 'url';
        $data['id'] = $url;
    }
    return $data;
}

/*
*inter函数其实不怎么好用
*解析部分已经全部移除了
*但是视频id的获取这里暂时懒得重写就吧inter函数暂时丢这里了
*后面有时间会把这部分代码也重写一下的
*/
function inter($str, $start, $end)
{
    $wd2 = '';
    if ($str && $start) {
        $arr = explode($start, $str);
        if (count($arr) > 1) {
            $wd = $arr[1];
            if ($end) {
                $arr2 = explode($end, $wd);
                if (count($arr2) > 1) {
                    $wd2 = $arr2[0];
                } else {
                    $wd2 = $wd;
                }
            } else {
                $wd2 = $wd;
            }
        }
    }
    return $wd2;
}

?>
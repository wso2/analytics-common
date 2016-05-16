/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

var gatewayPort = location.port - 9443 + 8243; //Calculate the port offset based gateway port.
var serverUrl = "https://" + location.hostname + ":" + gatewayPort + "/LogAnalyzerRestApi/1.0";
var client = new AnalyticsClient().init(null, null, serverUrl);
var canvasDiv = "#canvas";
var from = new Date(moment().subtract(29, 'days')).getTime();
var to = new Date(moment()).getTime();
var dataM = [];
var filteredMessage;
var nanoScrollerSelector = $(".nano");

var meta = {
    "names": ["timestamp", "Status", "Content", "actions"],
    "types": ["ordinal", "ordinal", "ordinal", "ordinal"]
};

var configTable = {
    key: "timestamp",
    title: "FilteredMessages",
    charts: [{
        type: "table",
        columns: ["timestamp", "Status", "Content", "actions"],
        columnTitles: ["Event Time Stamp", "Status", "Content", "Actions"]
    }
    ],
    width: $('body').width(),
    height: $('body').height(),
    padding: {"top": 40, "left": 80, "bottom": 70, "right": 100}
};

var template = '<a href="#" class="btn padding-reduce-on-grid-view" onclick= "viewFunction({{logTimestamp}},{{content}})"> <span class="fw-stack"> ' +
    '<i class="fw fw-ring fw-stack-2x"></i> <i class="fw fw-view fw-stack-1x"></i> </span> <span class="hidden-xs">View</span> </a>';

function initialize() {
    $(canvasDiv).html(gadgetUtil.getDefaultText());
    nanoScrollerSelector.nanoScroller();
}


$(document).ready(function () {
    initialize();
});

function fetch() {
    dataM.length = 0;
    var queryInfo;
    queryInfo = {
        tableName: "LOGANALYZER_APIKEY_STATUS",
        searchParams: {
            query: "status: \"" + filteredMessage + "\"",
            start: 0, //starting index of the matching record set
            count: 100 //page size for pagination
        }
    };
    client.search(queryInfo, function (d) {
        var obj = JSON.parse(d["message"]);
        if (d["status"] === "success") {
            for (var i = 0; i < obj.length; i++) {
                dataM.push([new Date(obj[i].timestamp).toUTCString(), obj[i].values.status, obj[i].values.content, Mustache.to_html(template, {
                    logTimestamp: "'" + obj[i].timestamp + "'",
                    content: "'" + obj[i].values.content + "'"
                })]);
            }
            drawTokenStatusTable();

        }
    }, function (error) {
        error.message = "Internal server error while data indexing.";
        onError(error);
    });
}

function drawTokenStatusTable() {
    try {
        $(canvasDiv).empty();
        var table = new vizg(
            [
                {
                    "metadata": this.meta,
                    "data": dataM
                }
            ],
            configTable
        );
        table.draw(canvasDiv);
        var dataTable = $('#FilteredMessages').DataTable({
            dom: '<"dataTablesTop"' +
            'f' +
            '<"dataTables_toolbar">' +
            '>' +
            'rt' +
            '<"dataTablesBottom"' +
            'lip' +
            '>'
        });
        nanoScrollerSelector[0].nanoscroller.reset();
        dataTable.on('draw', function () {
            nanoScrollerSelector[0].nanoscroller.reset();
        });
    } catch (error) {
        error.message = "Error while drawing log event chart.";
        error.status = "";
        onError(error);
    }
}
function publish(data) {
    gadgets.Hub.publish("publisher", data);
};


function subscribe(callback) {
    gadgets.HubSettings.onConnect = function () {
        gadgets.Hub.subscribe("api-keys-subscriber", function (topic, data, subscriber) {
            callback(topic, data, subscriber)
        });
    };
}

subscribe(function (topic, data, subscriber) {
    $(canvasDiv).html(gadgetUtil.getLoadingText());
    filteredMessage = data["selected"];
    fetch();
});

function viewFunction(timestamp, content) {
    publish({
        timestamp: timestamp,
        message: content
    });
}

function onError(msg) {
    $(canvasDiv).html(gadgetUtil.getErrorText(msg));
}
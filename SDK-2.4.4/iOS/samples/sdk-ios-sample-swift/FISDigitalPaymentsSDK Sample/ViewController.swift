//  ViewController.swift
//  FISDigitalPaymentsSDK Sample
//  Created by Zimmerman, Maxwell on 2/13/19.
//  Copyright © 2019 FIS. All rights reserved.
//

import FISDigitalPaymentsSDK
import WebKit
import UIKit

class ViewController: UIViewController, FDSWebEventListening {
    let domainWhitelist = ["epayments-crossdomain-fnc.billdomain.com", "epayments-epayui-fnc-3.money-movement.com", "epayments-epayui-uat-3.money-movement.com"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let webView = WKWebView()
        view = webView
        
        _ = FDSSDKManager.create(webView: webView, domainWhitelist: domainWhitelist, webEventListener: self)
        
        webView.load(URLRequest(url: URL(string: "https://epayments-crossdomain-fnc.billdomain.com/Mobile/Index.html")!))
    }
    
    func onEventReceived(webEvent: FDSWebEvent) {
        print(webEvent)
    }
}
